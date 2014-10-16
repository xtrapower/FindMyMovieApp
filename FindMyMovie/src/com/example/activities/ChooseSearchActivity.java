package com.example.activities;

import com.example.movie.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseSearchActivity extends Activity {

	private Button PopularSearchButton, LatestSearchButton,
			UpcomingSearchButton, titleSearchButton, discoverButton,
			personSearchButton, keywordSearchButton;
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

		PopularSearchButton = (Button) findViewById(R.id.PopularSearch);
		PopularSearchButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent();
				intent.putExtra("Popular", "Popular");
				intent.putExtra("Art", "Popular");
				intent.setClass(ChooseSearchActivity.this, ResultActivity.class);
				startActivity(intent);

			}
		});

		LatestSearchButton = (Button) findViewById(R.id.NowPlayingSearch);
		LatestSearchButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent();
				intent.putExtra("Latest", "Latest");
				intent.putExtra("Art", "Latest");
				intent.setClass(ChooseSearchActivity.this, ResultActivity.class);
				startActivity(intent);

			}
		});

		UpcomingSearchButton = (Button) findViewById(R.id.UpcomingSearch);
		UpcomingSearchButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent();
				intent.putExtra("Upcoming", "Upcoming");
				intent.putExtra("Art", "Upcoming");
				intent.setClass(ChooseSearchActivity.this, ResultActivity.class);
				startActivity(intent);

			}
		});

		titleSearchButton = (Button) findViewById(R.id.titleSearch);
		titleSearchButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				intent.setClass(ChooseSearchActivity.this,
						TitleSearchActivity.class);
				startActivity(intent);

			}

		});

		discoverButton = (Button) findViewById(R.id.discoverSearch);
		discoverButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				intent.setClass(ChooseSearchActivity.this,
						DiscoverActivity.class);
				startActivity(intent);
			}

		});

		personSearchButton = (Button) findViewById(R.id.personSearch);
		personSearchButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				intent.setClass(ChooseSearchActivity.this,
						PersonSearchActivity.class);
				startActivity(intent);
			}

		});

		keywordSearchButton = (Button) findViewById(R.id.keywordSearch);
		keywordSearchButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				intent.setClass(ChooseSearchActivity.this,
						KeywordSearchActivity.class);
				startActivity(intent);

			}

		});
	}

}