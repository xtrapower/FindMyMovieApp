package com.example.activities;

import java.util.concurrent.ExecutionException;

import com.example.fetcher.KeywordFetcher;
import com.example.fetcher.PersonFetcher;
import com.example.movie.R;
import com.example.movie.R.id;
import com.example.movie.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class PersonSearchActivity extends Activity {

	private Button search;
	private EditText firstNameEdit, lastNameEdit;
	private PersonFetcher pF;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupUI();

	}

	private void setupUI() {
		setContentView(R.layout.person_search_activity);
		setupUIElements();
	}

	private void setupUIElements() {

		setupET();
		setupButton();

	}

	private void setupET() {

		firstNameEdit = (EditText) findViewById(R.id.edit_first_name);
		lastNameEdit = (EditText) findViewById(R.id.edit_last_name);

	}

	private void setupButton() {

		search = (Button) findViewById(R.id.searchButton);
		search.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				pF = new PersonFetcher();
				String name = ""+firstNameEdit.getText().toString()+"%20"+lastNameEdit.getText().toString();
				pF.execute(name);
				startActivityWithExtra();

			}

		});
	}

	private void startActivityWithExtra() {
		Intent intent = new Intent();
		
		try {
			intent.putExtra("Person", pF.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		intent.putExtra("Art", "Person");
		intent.setClass(PersonSearchActivity.this, ResultActivity.class);
		startActivity(intent);

	}

}
