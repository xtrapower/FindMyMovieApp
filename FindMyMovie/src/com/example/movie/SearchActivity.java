package com.example.movie;

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

	private TextView actRating;
	private Button search;
	@SuppressWarnings("unused")
	private EditText titleEdit, genreEdit;
	private SeekBar ratingBar;
	private float ratingNum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupUI();
	}

	private void setupUI() {
		setContentView(R.layout.search_activity);
		setupUIElements();

	}

	private void setupUIElements() {

		setupTV();
		setupButton();
		setupET();
		setupSeekBar();

	}

	private void setupSeekBar() {

		ratingBar = (SeekBar) findViewById(R.id.seekBar_rating);
		ratingBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				ratingNum = (float) (progress * 0.1);
				actRating.setText("" + ratingNum);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}

			@Override
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
				startActivityWithExtra();

			}

		});
	}

	private void startActivityWithExtra() {
		Intent intent = new Intent();
		intent.putExtra("Titel",
				titleEdit.getText().toString().replaceAll(" ", "%20"));
		intent.setClass(SearchActivity.this, ResultActivity.class);
		startActivity(intent);

	}

	@SuppressWarnings("unused")
	private void setupTV() {

		TextView title = (TextView) findViewById(R.id.title_search);
		TextView genre = (TextView) findViewById(R.id.genre_search);
		TextView rating = (TextView) findViewById(R.id.rating_search);
		actRating = (TextView) findViewById(R.id.rating);
	}

}
