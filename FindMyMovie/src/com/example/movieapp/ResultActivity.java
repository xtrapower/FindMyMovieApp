package com.example.movieapp;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ResultActivity extends Activity {

	private MovieListAdapter mov_adapter;
	private ArrayList<Movie> movs;
	private ListView list;
	private Context context;
	MovieFetcher dF;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.movie_list_activity);
		init();
	}

	private void init() {
		context = this;
		movs = new ArrayList<Movie>();
		dF = new MovieFetcher();
		initListView();
	}

	private void initListView() {
		list = (ListView) findViewById(R.id.movieListView);
		String title = getIntent().getStringExtra("Titel");
		dF.execute(title);
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
				/*
				 * Movie mov = (Movie) list .getItemAtPosition(position);
				 * startReceiverActivity(mov);
				 */

			}
		});
	}

}
