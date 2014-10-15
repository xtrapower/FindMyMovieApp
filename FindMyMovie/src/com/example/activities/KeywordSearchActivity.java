package com.example.activities;

import java.util.concurrent.ExecutionException;

import com.example.fetcher.KeywordFetcher;
import com.example.helper.CommunicationHelper;
import com.example.helper.DialogHelper;
import com.example.movie.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class KeywordSearchActivity extends Activity {

	private Button searchButton;
	private EditText keywordEdit;
	private KeywordFetcher kFetcher;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupUI();

	}

	private void setupUI() {
		setContentView(R.layout.keyword_search_activity);
		context = this;
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

		searchButton = (Button) findViewById(R.id.searchButton);
		searchButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (CommunicationHelper
						.isNetAvailable(KeywordSearchActivity.this) == false) {
					DialogHelper.internetDialog(context);
				} else {

					startActivityWithExtra();
				}
			}
		});
	}

	private void startActivityWithExtra() {
		kFetcher = new KeywordFetcher();
		kFetcher.execute(keywordEdit.getText().toString());
		Intent intent = new Intent();
		try {
			intent.putExtra("Keyword", kFetcher.get());
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
