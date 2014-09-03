package com.example.activities;

import java.util.concurrent.ExecutionException;

import com.example.fetcher.DetailFetcher;
import com.example.helper.AppConfig;
import com.example.helper.Movie;
import com.example.helper.MovieDatabase;
import com.example.helper.AppConfig.Server;
import com.example.movie.R;
import com.example.movie.R.id;
import com.example.movie.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputFilter.LengthFilter;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends Activity {

	private MovieDatabase database;
	private Button addButton;
	private DetailFetcher dF;
	private TextView title, release_date, description, rating, genre;
	private Movie mov, mov_old;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getRequest();
		setupUI();
	}

	private void getRequest() {

		dF = new DetailFetcher();
		mov_old = (Movie) getIntent().getExtras().getParcelable(
				ResultActivity.OBJECT_KEY);
		dF.execute(mov_old.getId());

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

		/*
		 * release_date.setText("rr"); rating.setText("rrr");
		 * description.setText("rrrr"); genre.setText("rrrrr");
		 */
		WebView web = (WebView) findViewById(R.id.webView1);
		web.loadUrl(AppConfig.Server.URL_GET_IMAGE + mov.getImagePath());

	}

	private void setupButton() {

		addButton = (Button) findViewById(R.id.button1);
		addButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				database.insertData(mov);
				Toast.makeText(DetailActivity.this, "Hinzugefügt!", Toast.LENGTH_SHORT).show();
				
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
