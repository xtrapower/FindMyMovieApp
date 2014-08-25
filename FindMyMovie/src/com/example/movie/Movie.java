package com.example.movie;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class Movie  implements Parcelable {

	private int id;
	private String title, release_date, description, imagePath, firstGenre,
			secondGenre;
	private double rating;
	private float popular;

	public Movie(int id, String release_date, String title, double rating, float popular) {
		this.id = id;
		this.release_date = release_date;
		this.title = title;
		this.rating = rating;
		this.popular = popular;
	}

	public Movie(int id, String title, double rating, String firstGenre,
			String secondGenre, String release_date, String description,
			String imagePath) {
		this.id = id;
		this.title = title;
		this.rating = rating;
		this.firstGenre = firstGenre;
		this.secondGenre = secondGenre;
		this.release_date = release_date;
		this.description = description;
		this.imagePath = imagePath;
	}

	public Movie(JSONObject json) throws JSONException {
		this.id = json.getInt("id");
		this.release_date = json.getString("release_date");
		this.title = json.getString("title");
		this.rating = json.getDouble("vote_average");
		this.popular = (float) json.getDouble("popularity");

	}

	public Movie(JSONObject json, int i) throws JSONException {
		this.id = json.getInt("id");
		this.title = json.getString("title");
		this.rating = json.getDouble("vote_average");
		this.firstGenre = json.getJSONArray("genres").getJSONObject(0)
				.getString("name");
		this.secondGenre = json.getJSONArray("genres").getJSONObject(1)
				.getString("name");
		this.release_date = json.getString("release_date").substring(0, 4);
		this.description = json.getString("overview");
		this.imagePath = json.getString("poster_path");

	}
	
	public Movie(Parcel in) {
		this.id = in.readInt();
		this.title = in.readString();
		this.rating = in.readDouble();
		this.firstGenre = in.readString();
		this.secondGenre = in.readString();
		this.release_date = in.readString();
		this.description = in.readString();
		this.imagePath = in.readString();
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public double getRating() {

		return rating;
	}
	
	public float getPopularity() {

		return popular;
	}

	public String getFirstGenre() {

		return firstGenre;
	}

	public String getSecondGenre() {

		return secondGenre;
	}
	
	public String getGenre() {
		
		return firstGenre + "/" + secondGenre;
	}

	public String getReleaseDate() {

		return release_date;
	}

	public String getDescription() {

		return description;
	}

	public String getImagePath() {
		return imagePath;
	}

	@Override
	public String toString() {
		return title;
	}

	public String toJson() {
		return "{\"id\":\"" + id + "\",\"title\":\"" + title
				+ "\",\"vote_average\":\"" + rating + "\"}";
	}
	
	public int describeContents() {
		return 0;
	}

	
	public void writeToParcel(Parcel schlauch, int flags) {
		schlauch.writeInt(id);
		schlauch.writeString(title);
		schlauch.writeDouble(rating);
		schlauch.writeString(firstGenre);
		schlauch.writeString(secondGenre);
		schlauch.writeString(release_date);
		schlauch.writeString(description);
		schlauch.writeString(imagePath);

	}

	public static final Parcelable.Creator<Movie> CREATOR = new Creator<Movie>() {
		@Override
		public Movie createFromParcel(Parcel source) {
			return new Movie(source);
		}

		@Override
		public Movie[] newArray(int size) {
			return new Movie[size];
		}
	};

}