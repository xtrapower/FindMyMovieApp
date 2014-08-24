package com.example.movie;

import org.json.JSONException;
import org.json.JSONObject;

public final class Movie {

	private int id;
	private String title, release, description, imagePath, firstGenre,
			secondGenre;
	private double rating;
	private float popular;

	public Movie(int id, String release, String title, double rating, float popular) {
		this.id = id;
		this.release = release;
		this.title = title;
		this.rating = rating;
		this.popular = popular;
	}

	public Movie(int id, String title, double rating, String firstGenre,
			String secondGenre, String release, String description,
			String imagePath) {
		this.id = id;
		this.title = title;
		this.rating = rating;
		this.firstGenre = firstGenre;
		this.secondGenre = secondGenre;
		this.release = release;
		this.description = description;
		this.imagePath = imagePath;
	}

	public Movie(JSONObject json) throws JSONException {
		this.id = json.getInt("id");
		this.release = json.getString("release_date");
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
		this.release = json.getString("release_date").substring(0, 4);
		this.description = json.getString("overview");
		this.imagePath = json.getString("poster_path");

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

	public String getRelease() {

		return release;
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

}