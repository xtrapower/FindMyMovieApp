package com.example.activities;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import com.example.fetcher.MovieFetcher;
import com.example.helper.CommunicationHelper;
import com.example.helper.DialogHelper;
import com.example.helper.Movie;
import com.example.helper.MovieDatabase;
import com.example.helper.MovieListAdapter;
import com.example.movie.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ResultActivity extends Activity {

	private MovieListAdapter mov_adapter;
	private ListView list;
	private MovieFetcher mFetcher;
	public MovieDatabase database;
	private ArrayList<Movie> mov_list;

	public static final String OBJECT_KEY = "PARCABLE_OBJECT";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.movie_list_activity);
		if (CommunicationHelper.isNetAvailable(this) == false) {
			DialogHelper.internetDialog(this);
		} else {
			init();
		}
	}

	private void init() {

		initFetcher();
		initListView();
		initLocationDatabase();
	}

	private void initFetcher() {

		String art = getIntent().getStringExtra("Art");
		mFetcher = new MovieFetcher();

		switch (art) {

		case "Titel":
			String title = getIntent().getStringExtra("Titel");
			mFetcher.execute(title, art);
			break;

		case "Titel+Jahr":
			String titleYear = getIntent().getStringExtra("Titel");
			String year = getIntent().getStringExtra("Jahr");
			mFetcher.execute(titleYear, art, year);
			break;

		case "Keyword":
			String keyword = getIntent().getStringExtra("Keyword");
			mFetcher.execute(keyword, art);
			break;

		case "Person":
			String name = getIntent().getStringExtra("Person");
			mFetcher.execute(name, art);
			break;

		case "Discover":
			String genre = getIntent().getStringExtra("Genre");
			String yearDiscover = getIntent().getStringExtra("Jahr");
			String rating = getIntent().getStringExtra("Bewertung");
			String popular = getIntent().getStringExtra("Beliebt");
			mFetcher.execute(genre, art, yearDiscover, rating, popular);
			break;

		default:
			break;
		}
	}

	private void initLocationDatabase() {
		database = new MovieDatabase(this);
		database.open();
	}

	private void initListView() {
		list = (ListView) findViewById(R.id.movieListView);

		try {
			mov_list = mFetcher.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mov_adapter = new MovieListAdapter(this, mov_list);
		mov_adapter.notifyDataSetChanged();
		list.setAdapter(mov_adapter);

		if (mov_list.isEmpty()) {
			DialogHelper.errorDialog(this);
		}
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

				Movie mov = (Movie) list.getItemAtPosition(position);
				startReceiverActivity(mov);
			}
		});
	}

	private void startReceiverActivity(Movie mov) {
		Intent receiverActivity = new Intent(ResultActivity.this,
				DetailActivity.class);
		receiverActivity.putExtra(OBJECT_KEY, mov);
		startActivity(receiverActivity);
	}

}
