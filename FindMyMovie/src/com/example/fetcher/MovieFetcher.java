package com.example.fetcher;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.helper.AppConfig;
import com.example.helper.Movie;

import android.os.AsyncTask;

public class MovieFetcher extends AsyncTask<String, Float, ArrayList<Movie>> {

	private ArrayList<Movie> movs;

	@Override
	protected ArrayList<Movie> doInBackground(String... params) {

		movs = new ArrayList<Movie>();

		switch (params[1]) {
		
		// Popular Feature
		
		case "Popular":
			
			
			String PopularMoviesJSON = processHttpRequest(AppConfig.Server.URL_GET_STATIC_SEARCH + "popular" + "?" + AppConfig.Server.API_KEY);
			try {
				readMovieJSON(PopularMoviesJSON);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return movs;
			
		
		case "Latest":
				
				
				String LatestMoviesJSON = processHttpRequest(AppConfig.Server.URL_GET_STATIC_SEARCH + "now_playing" + "?" + AppConfig.Server.API_KEY);
				try {
					readMovieJSON(LatestMoviesJSON);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return movs;
				
				
		case "Upcoming":
			
			
			String UpcomingMoviesJSON = processHttpRequest(AppConfig.Server.URL_GET_STATIC_SEARCH + "upcoming" + "?" + AppConfig.Server.API_KEY);
			try {
				readMovieJSON(UpcomingMoviesJSON);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return movs;
			

		case "Titel":

			String MovieJSON = processHttpRequest(AppConfig.Server.URL_GET_SEARCH
					+ params[0] + "&" + AppConfig.Server.API_KEY);
			try {
				readMovieJSON(MovieJSON);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return movs;

		case "Titel+Jahr":

			String MovieYearJSON = processHttpRequest(AppConfig.Server.URL_GET_SEARCH
					+ params[0]
					+ "&"
					+ AppConfig.Server.API_KEY
					+ AppConfig.Server.URL_GET_SEARCH_EXTRA + params[2]);
			try {
				readMovieJSON(MovieYearJSON);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return movs;

		case "Keyword":
			String KeywordJSON = processHttpRequest(AppConfig.Server.URL_GET_KEYWORD_SEARCH
					+ params[0] + "/movies?" + AppConfig.Server.API_KEY);
			try {
				readMovieJSON(KeywordJSON);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return movs;

		case "Person":
			String PersonJSON = processHttpRequest(AppConfig.Server.URL_GET_PERSON_SEARCH
					+ params[0] + "/movie_credits?" + AppConfig.Server.API_KEY);
			try {
				readPersonJSON(PersonJSON);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return movs;

		case "Discover":
			String GenreJSON = processHttpRequest(AppConfig.Server.URL_GET_GENRE_SEARCH
					+ AppConfig.Server.API_KEY
					+ AppConfig.Server.URL_GET_GENRE_EXTRA
					+ params[0]
					+ AppConfig.Server.URL_GET_SEARCH_EXTRA
					+ params[2]
					+ AppConfig.Server.URL_GET_RATING_EXTRA
					+ params[3]
					+ AppConfig.Server.URL_GET_POPULAR_EXTRA + params[4]);
			try {
				readMovieJSON(GenreJSON);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return movs;

		default:
			return movs;
		}
	}

	private void readMovieJSON(String json_string) throws JSONException {
		JSONObject json = new JSONObject(json_string);
		JSONArray movie = json.getJSONArray("results");
		for (int i = 0; i < movie.length(); i++) {
			Movie mov = new Movie(movie.getJSONObject(i));
			movs.add(mov);
		}
	}

	private void readPersonJSON(String json_string) throws JSONException {
		JSONObject json = new JSONObject(json_string);
		JSONArray movie = json.getJSONArray("cast");
		for (int i = 0; i < movie.length(); i++) {
			Movie mov = new Movie(movie.getJSONObject(i), 0.0f);
			movs.add(mov);
		}
	}

	private String processHttpRequest(String url) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response;
		String responseString = null;
		try {

			response = httpclient.execute(new HttpGet(url));
			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				responseString = out.toString();

			} else {
				response.getEntity().getContent().close();
				throw new IOException(statusLine.getReasonPhrase());
			}
		} catch (Exception e) {
			// Error Handling
		}
		return responseString;
	}

	protected void onPostExecute(ArrayList<String> result) {

	}

}