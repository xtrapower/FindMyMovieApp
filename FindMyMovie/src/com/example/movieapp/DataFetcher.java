package com.example.movieapp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

public class DataFetcher extends
		AsyncTask<String, Float, String> {

	String API_KEY ="f4abf758a9edc14dedcad5f120ea63ab";
	String pois;


	@Override
	protected String doInBackground(String... params) {

		pois = new String();
		String JSON = processHttpRequest("http://api.themoviedb.org/3/search/movie?query="+params[0]+"&api_key="+API_KEY);
		Log.e("test", JSON);
		return pois;
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