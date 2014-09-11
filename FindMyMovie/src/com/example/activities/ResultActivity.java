package com.example.activities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.ExecutionException;

import com.example.fetcher.MovieFetcher;
import com.example.helper.DialogHelper;
import com.example.helper.Movie;
import com.example.helper.MovieDatabase;
import com.example.helper.MovieListAdapter;
import com.example.movie.R;
import com.example.movie.R.id;
import com.example.movie.R.layout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ResultActivity extends Activity {

	private MovieListAdapter mov_adapter;
	private ListView list;
	private MovieFetcher dF;
	public MovieDatabase database;
	private ArrayList<Movie> mov_list;

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

		String art = getIntent().getStringExtra("Art");
		dF = new MovieFetcher();

		switch (art) {
		 
		case "Titel":
				String title = getIntent().getStringExtra("Titel");
				dF.execute(title, art);
			break;
			
		case "Titel+Jahr":
				String titleYear = getIntent().getStringExtra("Titel");
				String year = getIntent().getStringExtra("Jahr");
				dF.execute(titleYear, art, year);
			break;
		
		case "Keyword":
				String keyword = getIntent().getStringExtra("Keyword");
			 	dF.execute(keyword, art);
			break;
			
		case "Person":
				String name = getIntent().getStringExtra("Person");
				dF.execute(name, art);
		break;
		
		case "Para":
				String genre = getIntent().getStringExtra("Genre");
				String yearPara = getIntent().getStringExtra("Jahr");
				String rating = getIntent().getStringExtra("Bewertung");
				String popular = getIntent().getStringExtra("Beliebt");
				dF.execute(genre, art, yearPara, rating, popular);
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
			mov_list = dF.get();
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
		
		//
		// Falls Liste leer, wird Fehler angezeigt
		//
		
		if (mov_list.isEmpty()) {
			showAlertDialog();
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

	private void showAlertDialog() {
		AlertDialog errorDialog = DialogHelper.getErrorDialog(this, "Fehler",
					"Keine Filme gefunden!", true);

			errorDialog.show();

	}

	private void startReceiverActivity(Movie mov) {
		Intent receiverActivity = new Intent(ResultActivity.this,
				DetailActivity.class);
		receiverActivity.putExtra(OBJECT_KEY, mov);
		startActivity(receiverActivity);
	}

}
