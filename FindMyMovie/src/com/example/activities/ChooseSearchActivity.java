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


public class ChooseSearchActivity extends Activity {

	private Button titleS, paraS, personS, keywordS;
	@SuppressWarnings("unused")
	private EditText titleEdit, yearEdit;
	private Intent intent;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupUI();
	}

	private void setupUI() {
		setContentView(R.layout.search_options);
		setupUIElements();

	}

	private void setupUIElements() {

		setupButton();
		intent = new Intent();
	}

	private void setupButton() {

		titleS = (Button) findViewById(R.id.titleSearch);
		titleS.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				intent.setClass(ChooseSearchActivity.this,
						TitleSearchActivity.class);
				startActivity(intent);

			}

		});
		
		  paraS = (Button) findViewById(R.id.paraSearch);
		  paraS.setOnClickListener(new View.OnClickListener() {
		  
		  @Override public void onClick(View v) { 
			  intent.setClass(ChooseSearchActivity.this,
						ParaSearchActivity.class);
				startActivity(intent);
		 }
		  
		  });
		
		 personS = (Button) findViewById(R.id.personSearch);
		  personS.setOnClickListener(new View.OnClickListener() {
		  
		  @Override public void onClick(View v) {
			  intent.setClass(ChooseSearchActivity.this,
						PersonSearchActivity.class);
				startActivity(intent);
		 }
		  
		 });
		 
		keywordS = (Button) findViewById(R.id.keywordSearch);
		keywordS.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				intent.setClass(ChooseSearchActivity.this,
						KeywordSearchActivity.class);
				startActivity(intent);

			}

		});
	}

}