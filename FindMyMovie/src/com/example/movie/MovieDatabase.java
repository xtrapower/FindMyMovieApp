package com.example.movie;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MovieDatabase {
	private LocationDatabaseHelper helper;
	private SQLiteDatabase db;

	public MovieDatabase(Context context) {
		helper = new LocationDatabaseHelper(context, "moviesearch", null, 1);
	}

	public void open() throws SQLException {
		try {
			db = helper.getWritableDatabase();
		} catch (SQLException e) {
			db = helper.getReadableDatabase();
		}
	}

	public void close() {
		db.close();
	}

	public void insertData(Movie mov) {
		ContentValues newMovie = new ContentValues();
		newMovie.put("id", mov.getId());
		newMovie.put("release_date", mov.getReleaseDate());
		newMovie.put("title", mov.getTitle().replaceAll("\'", "´"));
		newMovie.put("rating", mov.getRating());
		newMovie.put("popular", mov.getPopularity());

		Cursor cursor = db.query("movies", null, "id" + "=?",
				new String[] { mov.getId() + "" }, null, null, null);
		if (cursor.getCount() > 0) {
			db.update("movies", newMovie, "id" + "=?",
					new String[] { mov.getId() + "" });
		} else {
			db.insert("movies", null, newMovie);
		}
	}

	public void removeMovie(Movie mov) {

		String whereClause = "id" + " = '" + mov.getId() + "' AND " + "release_date"
				+ " = '" + mov.getReleaseDate() + "' AND " + "title" + " = '"
				+ mov.getTitle() + "' AND " + "rating" + " = '"
				+ mov.getRating() + "' AND " + "popular" + " = '"
				+ mov.getPopularity() + "'";

		db.delete("Movies", whereClause, null);

	}

	public ArrayList<Movie> getAllItems() {
		ArrayList<Movie> items = new ArrayList<Movie>();
		Cursor cursor = db.query("Movies", new String[] { "id", "release_date",
				"title", "rating", "popular" }, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				int id = cursor.getInt(0);
				String release_date = cursor.getString(1);
				String title = cursor.getString(2);
				double rating = cursor.getDouble(3);
				float popular = cursor.getFloat(4);

				items.add(new Movie(id, release_date, title, rating, popular));
			} while (cursor.moveToNext());
		}
		return items;
	}

	private class LocationDatabaseHelper extends SQLiteOpenHelper {

		private static final String DATABASE_CREATE = "create table "
				+ "Movies" + " (" + "id"
				+ " integer primary key autoincrement, " + "release_date"
				+ " text not null, " + "title" + " text not null, " + "rating"
				+ " real not null, " + "popular" + " real not null )";

		public LocationDatabaseHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);

		}

	}

}
