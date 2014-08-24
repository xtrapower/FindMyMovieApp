package com.example.movie;

import java.net.URL;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class DetailActivity extends Activity {

	Bitmap mIcon_val;
	URL newurl;
	MovieDatabase database;
	PopupWindow popUp;
	Button addButton;
	NotificationCompat.Builder mBuilder;
	DetailFetcher dF;
	TextView title, release, description, rating, genre;
	Movie mov;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		dF = new DetailFetcher();
		dF.execute((int) getIntent().getExtras().get("ID"));
		setupUI();
	}

	private void setupUI() {
		setContentView(R.layout.detail);

		setupTV();
		setupButton();
		initLocationDatabase();
		setupText();
	}

	private void setupText() {
		try {
			mov = dF.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		title.setText(mov.getTitle());
		release.setText(mov.getRelease());
		rating.setText("" + mov.getRating());
		description.setText(mov.getDescription());
		genre.setText(mov.getFirstGenre()+"/"+mov.getSecondGenre());
		
		WebView web = (WebView) findViewById(R.id.webView1);
		web.loadUrl("http://image.tmdb.org/t/p/w150" + mov.getImagePath());

	}

	private void setupButton() {

		addButton = (Button) findViewById(R.id.button1);
		addButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Movie mov = (Movie) list .getItemAtPosition(position);
				// database.insertData(mov);

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
		release = (TextView) findViewById(R.id.detailYearText);
		description = (TextView) findViewById(R.id.detailDescriptionText);
		genre = (TextView) findViewById(R.id.detailGenreText);

	}

}
