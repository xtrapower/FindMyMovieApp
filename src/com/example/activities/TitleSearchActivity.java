package com.example.activities;

import com.example.movie.R;
import com.example.movie.R.id;
import com.example.movie.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class TitleSearchActivity extends Activity {

	private TextView actRating;
	private Button search;
	@SuppressWarnings("unused")
	private EditText titleEdit, yearEdit;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupUI();
	}

	private void setupUI() {
		setContentView(R.layout.title_search_activity);
		setupUIElements();

	}

	private void setupUIElements() {

		setupTV();
		setupButton();
		setupET();

	}

	private void setupET() {

		titleEdit = (EditText) findViewById(R.id.edit_title);
		yearEdit = (EditText) findViewById(R.id.edit_year);


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
		if (yearEdit.getText().toString() != "") {
			intent.putExtra("Art", "Titel+Jahr");
			intent.putExtra("Jahr", yearEdit.getText().toString());
		} else {
			intent.putExtra("Art", "Titel");
		}
		intent.setClass(TitleSearchActivity.this, ResultActivity.class);

		startActivity(intent);

	}

	@SuppressWarnings("unused")
	private void setupTV() {

		TextView title = (TextView) findViewById(R.id.title_search);
	}

}
