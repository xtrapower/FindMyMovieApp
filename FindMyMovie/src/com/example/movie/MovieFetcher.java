package com.example.movie;

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

import android.os.AsyncTask;

public class MovieFetcher extends AsyncTask<String, Float, ArrayList<Movie>> {

	String API_KEY = "f4abf758a9edc14dedcad5f120ea63ab";
	ArrayList<Movie> movs;

	@Override
	protected ArrayList<Movie> doInBackground(String... params) {

		movs = new ArrayList<Movie>();
		String JSON = processHttpRequest("http://api.themoviedb.org/3/search/movie?query="
				+ params[0] + "&api_key=" + API_KEY);
		try {
			readJSON(JSON);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movs;
	}

	private void readJSON(String json_string) throws JSONException {
		JSONObject json = new JSONObject(json_string);
		JSONArray movie = json.getJSONArray("results");
		for (int i = 0; i < 10; i++) {
			Movie mov = new Movie(movie.getJSONObject(i));
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