package com.example.movie;

import java.util.concurrent.ExecutionException;

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
	private MovieFetcher dF;
	public MovieDatabase database;

	public static final String OBJECT_KEY = "PARCABLE_OBJECT";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.movie_list_activity);
		init();
	}

	private void init() {

		initFetcher();
		initListView();
		initLocationDatabase();
	}

	private void initFetcher() {
		
		String title = getIntent().getStringExtra("Titel");
		dF = new MovieFetcher();
		dF.execute(title);
	}

	private void initLocationDatabase() {
		database = new MovieDatabase(this);
		database.open();
	}

	private void initListView() {
		list = (ListView) findViewById(R.id.movieListView);
		
		try {
			mov_adapter = new MovieListAdapter(this, dF.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mov_adapter.notifyDataSetChanged();
		list.setAdapter(mov_adapter);
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
