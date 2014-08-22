package com.example.movie;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class WatchListActivity extends Activity {

	private MovieListAdapter mov_adapter;
	private ArrayList<Movie> movs;
	private ListView list;
	private AlertDialog errorDialog;

	MovieFetcher dF;
	MovieDatabase database;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.movie_list_activity);
		init();
	}

	private void init() {
		movs = new ArrayList<Movie>();
		dF = new MovieFetcher();
		database = new MovieDatabase(this);
		database.open();
		initData();
		initListView();

	}

	private void initData() {

		movs.clear();
		movs.addAll(database.getAllItems());
		mov_adapter = new MovieListAdapter(this, movs);
		mov_adapter.notifyDataSetChanged();

		checkEmpty();
		
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
		list.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

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