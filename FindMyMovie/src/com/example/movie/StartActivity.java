package com.example.movie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends Activity {

	private Button search, watchlist;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupUI();
	}

	private void setupUI() {
		setContentView(R.layout.activity_main);
		setupTV();
		setupButtons();
	}

	private void setupButtons() {

		search = (Button) findViewById(R.id.search);

		search.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				intent = new Intent();
				intent.setClass(StartActivity.this, SearchActivity.class);
				startActivity(intent);
			}
		});
		watchlist = (Button) findViewById(R.id.watchlist);
		watchlist.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				intent = new Intent();
				intent.setClass(StartActivity.this, WatchListActivity.class);
				startActivity(intent);
			}
		});
	}

	@SuppressWarnings("unused")
	private void setupTV() {

		TextView title = (TextView) findViewById(R.id.title);
		TextView below_title = (TextView) findViewById(R.id.bTitle);
	}

}
