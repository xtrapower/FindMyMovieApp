package com.example.activities;

import java.util.ArrayList;
import java.util.Collections;

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
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class WatchListActivity extends Activity {

	private MovieListAdapter mov_adapter;
	private ArrayList<Movie> movs;
	private ListView list;
	private AlertDialog errorDialog;
	private MovieDatabase database;
	public static final String OBJECT_KEY = "PARCABLE_OBJECT";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.movie_list_activity);
		init();
	}

	private void init() {

		initDatabase();
		initData();
		initListView();

	}

	private void initDatabase() {
		database = new MovieDatabase(this);
		database.open();

	}

	private void initData() {

		movs = new ArrayList<Movie>();
		mov_adapter = new MovieListAdapter(this, movs);
		updateList();
	}

	private void checkEmpty() {
		if (database.getAllItems().isEmpty()) {
			errorDialog = DialogHelper.getErrorDialog(this, "Fehler",
					"Keine Filme in der WatchList Datenbank enthalten!", true);

			errorDialog.show();

		}

	}

	private void initListView() {
		list = (ListView) findViewById(R.id.movieListView);
		list.setAdapter(mov_adapter);
		mov_adapter.notifyDataSetChanged();
		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

					Movie mov = (Movie) list.getItemAtPosition(arg2);
					Intent intent = new Intent(WatchListActivity.this, DetailActivity.class);
					intent.putExtra(OBJECT_KEY, mov);
					intent.putExtra("WatchList", true);
					startActivity(intent);
			}
		
		});
		
		list.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				
				Toast.makeText(WatchListActivity.this, "Entfernt!", Toast.LENGTH_SHORT).show();
				removeMovieAtPosition(position);
				return false;

			}
		});
	}

	private void removeMovieAtPosition(int position) {
		if (list.getItemAtPosition(position) == null) {
			return;
		} else {
			database.removeMovie((Movie) list.getItemAtPosition(position));
			updateList();
		}

	}

	private void updateList() {
		movs.clear();
		movs.addAll(database.getAllItems());
		mov_adapter.notifyDataSetChanged();
		checkEmpty();
	}
}
