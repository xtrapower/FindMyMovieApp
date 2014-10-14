package com.example.activities;

import java.util.concurrent.ExecutionException;

import com.example.fetcher.PersonFetcher;
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

public class PersonSearchActivity extends Activity {

	private Button searchButton;
	private EditText firstNameEdit, lastNameEdit;
	private PersonFetcher pFetcher;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupUI();

	}

	private void setupUI() {
		setContentView(R.layout.person_search_activity);
		context = this;
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

		searchButton = (Button) findViewById(R.id.searchButton);
		searchButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (CommunicationHelper
						.isNetAvailable(PersonSearchActivity.this) == false) {
					DialogHelper.internetDialog(context);
				} else {

					startActivityWithExtra();
				}
			}

		});
	}

	private void startActivityWithExtra() {
		pFetcher = new PersonFetcher();
		String name = "" + firstNameEdit.getText().toString() + "%20"
				+ lastNameEdit.getText().toString();
		pFetcher.execute(name);
		Intent intent = new Intent();

		try {
			intent.putExtra("Person", pFetcher.get());
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
