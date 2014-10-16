package com.example.activities;

import java.util.HashMap;

import com.example.movie.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

public class DiscoverActivity extends Activity {

	private Button searchButton;
	private EditText yearEdit;
	private CheckBox popularCheckBox;
	private SeekBar ratingBar;
	private float ratingNum;
	private Spinner genreSpinner;
	private TextView seekBarNum;
	private HashMap<String, Integer> genre;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupUI();
	}

	private void setupUI() {
		setContentView(R.layout.discover_search_activity);
		setupHashmap();
		setupUIElements();

	}

	private void setupHashmap() {

		String[] genresNames = getResources()
				.getStringArray(R.array.genre_name);
		int[] genresIDs = getResources().getIntArray(R.array.genre_ids);

		genre = new HashMap<String, Integer>();
		for (int i = 0; i < genresNames.length; i++) {
			genre.put(genresNames[i], genresIDs[i]);
		}

	}

	private void setupUIElements() {

		setupTV();
		setupButton();
		setupET();
		setupCB();
		setupSpinner();
		setupSeekBar();

	}

	private void setupCB() {

		popularCheckBox = (CheckBox) findViewById(R.id.popularCheckBox);

	}

	private void setupSpinner() {
		genreSpinner = (Spinner) findViewById(R.id.spinner1);

	}

	private void setupSeekBar() {

		ratingBar = (SeekBar) findViewById(R.id.ratingBar);
		ratingBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				ratingNum = (float) (progress * 0.1);
				seekBarNum.setText("" + ratingNum);
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

		yearEdit = (EditText) findViewById(R.id.yearEdit);

	}

	private void setupButton() {

		searchButton = (Button) findViewById(R.id.searchButton);
		searchButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivityWithExtra();
			}

		});
	}

	private void startActivityWithExtra() {
		Intent intent = new Intent();
		intent.setClass(DiscoverActivity.this, ResultActivity.class);
		intent.putExtra("Genre",""+ genre.get(genreSpinner.getSelectedItem().toString()).toString());
		intent.putExtra("Art", "Discover");
		intent.putExtra("Jahr", yearEdit.getText().toString());
		intent.putExtra("Bewertung", seekBarNum.getText().toString());

		if (popularCheckBox.isChecked()) {
			intent.putExtra("Beliebt", "10");
		} else {
			intent.putExtra("Beliebt", "");
		}
		startActivity(intent);

	}

	private void setupTV() {

		seekBarNum = (TextView) findViewById(R.id.seekBarNum);
	}

}
