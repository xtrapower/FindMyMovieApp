package com.example.movie;

import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends Activity {

	private MovieDatabase database;
	private Button addButton;
	private DetailFetcher dF;
	private TextView title, release_date, description, rating, genre;
	private Movie mov;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getRequest();
		setupUI();
	}

	private void getRequest() {

		dF = new DetailFetcher();
		mov = (Movie) getIntent().getExtras().getParcelable(
				ResultActivity.OBJECT_KEY);
		dF.execute(mov.getId());

		try {
			mov = dF.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void setupUI() {
		setContentView(R.layout.detail);

		setupTV();
		initLocationDatabase();
		setupButton();
		setupText();
	}

	private void setupText() {

		title.setText(mov.getTitle());
		release_date.setText(mov.getReleaseDate());
		rating.setText("" + mov.getRating());
		description.setText(mov.getDescription());
		genre.setText(mov.getGenre());

		WebView web = (WebView) findViewById(R.id.webView1);
		web.loadUrl(AppConfig.Server.URL_GET_IMAGE + mov.getImagePath());

	}

	private void setupButton() {

		addButton = (Button) findViewById(R.id.button1);
		addButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				database.insertData(mov);

			}
		});

	}

	private void initLocationDatabase() {
		database = new MovieDatabase(this);
		database.open();

	}

	private void setupTV() {

		title = (TextView) findViewById(R.id.detailTitle);
		rating = (TextView) findViewById(R.id.detailRatingText);
		release_date = (TextView) findViewById(R.id.detailYearText);
		description = (TextView) findViewById(R.id.detailDescriptionText);
		genre = (TextView) findViewById(R.id.detailGenreText);

	}

}
