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

import android.os.AsyncTask;

public class KeywordFetcher extends AsyncTask<String, Float, String> {

	@Override
	protected String doInBackground(String... params) {
		String id = "";
		String JSON = processHttpRequest(AppConfig.Server.URL_GET_KEYWORD_ID
				+ params[0] + "&" + AppConfig.Server.API_KEY);
		try {
			id = readJSON(JSON);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	private String readJSON(String json_string) throws JSONException {
		JSONObject json = new JSONObject(json_string);
		JSONArray kwID = json.getJSONArray("results");
		return kwID.getJSONObject(0).getString("id").toString();

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