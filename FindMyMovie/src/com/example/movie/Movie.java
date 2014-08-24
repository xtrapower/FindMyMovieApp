package com.example.movie;

import org.json.JSONException;
import org.json.JSONObject;



public final class Movie {

	private int id;
	private String title, release, description;
	private double rating;

	public Movie(int id, String title, double rating) {
		this.id = id;
		this.title = title;
		this.rating = rating;
	}
	
	public Movie(int id, String title, double rating, /*String genre,*/ String release, String description) {
		this.id = id;
		this.title = title;
		this.rating = rating;
		//this.genre = genre;
		this.release = release;
		this.description = description;
	}

	public Movie(JSONObject json) throws JSONException {
		this.id = json.getInt("id");
		this.title = json.getString("title");
		this.rating = json.getDouble("vote_average");

	}
	
	public Movie(JSONObject json, int i) throws JSONException {
		this.id = json.getInt("id");
		this.title = json.getString("title");
		this.rating = json.getDouble("vote_average");
		//this.genre = json.getString("title");
		this.release = json.getString("release_date").substring(0, 3);
		this.description = json.getString("overview");

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
	
	/*public String getGenre() {

		return genre;
	}*/
	
	public String getRelease() {

		return release;
	}
	public String getDescription() {

		return description;
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