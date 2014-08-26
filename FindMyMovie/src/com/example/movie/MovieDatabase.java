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
		helper = new LocationDatabaseHelper(context,
				AppConfig.Data.DATABASE_KEY, null,
				AppConfig.Data.DATABASE_VERSION);
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
		newMovie.put(AppConfig.Data.ID_KEY, mov.getId());
		newMovie.put(AppConfig.Data.RELEASE_KEY, mov.getReleaseDate());
		newMovie.put(AppConfig.Data.TITLE_KEY,
				mov.getTitle().replaceAll("\'", "´"));
		newMovie.put(AppConfig.Data.RATING_KEY, mov.getRating());
		newMovie.put(AppConfig.Data.POPULAR_KEY, mov.getPopularity());

		Cursor cursor = db.query(AppConfig.Data.TABLE_KEY, null,
				AppConfig.Data.ID_KEY + "=?",
				new String[] { mov.getId() + "" }, null, null, null);
		if (cursor.getCount() > 0) {
			db.update(AppConfig.Data.TABLE_KEY, newMovie, AppConfig.Data.ID_KEY
					+ "=?", new String[] { mov.getId() + "" });
		} else {
			db.insert(AppConfig.Data.TABLE_KEY, null, newMovie);
		}
	}

	public void removeMovie(Movie mov) {

		String whereClause = AppConfig.Data.ID_KEY + " = '" + mov.getId()
				+ "' AND " + AppConfig.Data.RELEASE_KEY + " = '"
				+ mov.getReleaseDate() + "' AND " + AppConfig.Data.TITLE_KEY
				+ " = '" + mov.getTitle() + "' AND "
				+ AppConfig.Data.RATING_KEY + " = '" + mov.getRating()
				+ "' AND " + AppConfig.Data.POPULAR_KEY + " = '"
				+ mov.getPopularity() + "'";

		db.delete(AppConfig.Data.TABLE_KEY, whereClause, null);

	}

	public ArrayList<Movie> getAllItems() {
		ArrayList<Movie> items = new ArrayList<Movie>();
		Cursor cursor = db.query(AppConfig.Data.TABLE_KEY, new String[] {
				AppConfig.Data.ID_KEY, AppConfig.Data.RELEASE_KEY,
				AppConfig.Data.TITLE_KEY, AppConfig.Data.RATING_KEY,
				AppConfig.Data.POPULAR_KEY }, null, null, null, null, null);
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
				+ AppConfig.Data.TABLE_KEY + " (" + AppConfig.Data.ID_KEY
				+ " integer primary key autoincrement, "
				+ AppConfig.Data.RELEASE_KEY + " text not null, "
				+ AppConfig.Data.TITLE_KEY + " text not null, "
				+ AppConfig.Data.RATING_KEY + " real not null, "
				+ AppConfig.Data.POPULAR_KEY + " real not null )";

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
