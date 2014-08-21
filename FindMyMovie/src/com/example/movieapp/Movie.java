package com.example.movieapp;

import org.json.JSONException;
import org.json.JSONObject;



public final class Movie {

	private final int id;
	private final String title;
	private final double rating;

	public Movie(int id, String title, double rating) {
		this.id = id;
		this.title = title;
		this.rating = rating;
	}

	public Movie(JSONObject json) throws JSONException {
		this.id = json.getInt("id");
		this.title = json.getString("title");
		this.rating = json.getDouble("vote_average");

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

	@Override
	public String toString() {
		return title;
	}

	public String toJson() {
		return "{\"id\":\"" + id + "\",\"title\":\"" + title
				+ "\",\"vote_average\":\"" + rating + "\"}";
	}

	

	}