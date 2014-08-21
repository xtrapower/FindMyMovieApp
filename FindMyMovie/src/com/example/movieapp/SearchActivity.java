package com.example.movieapp;

import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class SearchActivity extends Activity {

	TextView title, genre, rating, actRating;
	Button search;
	EditText titleEdit, genreEdit;
	SeekBar ratingBar;
	float ratingNum;
	MovieFetcher dF;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupUI();
	}

	private void setupUI() {
		setContentView(R.layout.search_activity);
		dF = new MovieFetcher();
		setupTV();
		setupButton();
		setupET();
		setupSeekBar();

	}

	private void setupSeekBar() {

		ratingBar = (SeekBar) findViewById(R.id.seekBar_rating);
		ratingBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				ratingNum = (float) (progress * 0.1);
				actRating.setText("" + ratingNum);
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}

			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}
		});
	}

	private void setupET() {

		titleEdit = (EditText) findViewById(R.id.edit_title);
		genreEdit = (EditText) findViewById(R.id.edit_genre);

	}

	private void setupButton() {

		search = (Button) findViewById(R.id.searchButton);
		search.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent();
				intent.putExtra("Titel", titleEdit.getText().toString());
				intent.setClass(SearchActivity.this, ResultActivity.class);
				startActivity(intent);
			}
		});
	}

	private void setupTV() {

		title = (TextView) findViewById(R.id.title_search);
		genre = (TextView) findViewById(R.id.genre_search);
		rating = (TextView) findViewById(R.id.rating_search);
		actRating = (TextView) findViewById(R.id.rating);
	}

}
