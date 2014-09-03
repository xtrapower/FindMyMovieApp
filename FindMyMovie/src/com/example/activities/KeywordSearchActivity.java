package com.example.activities;

import java.util.concurrent.ExecutionException;

import com.example.fetcher.KeywordFetcher;
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

public class KeywordSearchActivity extends Activity {

	private Button search;
	private EditText keywordEdit;
	private KeywordFetcher kF;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setupUI();

	}

	private void setupUI() {
		setContentView(R.layout.keyword_search_activity);
		setupUIElements();
	}

	private void setupUIElements() {

		setupET();
		setupButton();

	}

	private void setupET() {

		keywordEdit = (EditText) findViewById(R.id.edit_title);

	}

	private void setupButton() {

		search = (Button) findViewById(R.id.searchButton);
		search.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				kF = new KeywordFetcher();
				kF.execute(keywordEdit.getText().toString());
				startActivityWithExtra();

			}

		});
	}

	private void startActivityWithExtra() {
		Intent intent = new Intent();
		try {
			intent.putExtra("Keyword", kF.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		intent.putExtra("Art", "Keyword");
		intent.setClass(KeywordSearchActivity.this, ResultActivity.class);
		startActivity(intent);

	}

}
