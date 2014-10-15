package com.example.activities;

import java.util.concurrent.ExecutionException;

import com.example.fetcher.DetailFetcher;
import com.example.helper.AppConfig;
import com.example.helper.Movie;
import com.example.helper.MovieDatabase;
import com.example.movie.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends YouTubeBaseActivity implements
		YouTubePlayer.OnInitializedListener {

	static private final String YOUTUBE_DEVELOPER_KEY = "AIzaSyBkSGm3-dVzW5bMo-BqG7ZgUsLmGYpGsfs";

	private MovieDatabase database;
	private Button addButton;
	private DetailFetcher dFetcher;
	private TextView title, release_date, description, rating, genre;
	private Movie mov, mov_old;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getRequest();
		setupUI();
	}

	private void getRequest() {

		dFetcher = new DetailFetcher();
		mov_old = (Movie) getIntent().getExtras().getParcelable(
				ResultActivity.OBJECT_KEY);
		dFetcher.execute(mov_old.getId());

		try {
			mov = dFetcher.get();
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
		setupYoutubePlayer();
	}

	private void setupYoutubePlayer() {

		YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
		youTubeView.initialize(YOUTUBE_DEVELOPER_KEY, this);
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
		if (getIntent().getExtras().get("WatchList") != null) {
			addButton.setVisibility(View.INVISIBLE);
		} else {
			addButton.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {

					if (checkDouble()) {
						Toast.makeText(DetailActivity.this,
								"Movie already in WatchList!",
								Toast.LENGTH_SHORT).show();
					} else {
						database.insertData(mov);
						Toast.makeText(DetailActivity.this, "Added!",
								Toast.LENGTH_SHORT).show();
					}
				}
			});
		}

	}

	private void initLocationDatabase() {
		database = new MovieDatabase(this);
		database.open();

	}

	private boolean checkDouble() {
		boolean doubleMovie = false;
		for (int i = 0; i < database.getAllItems().size(); i++) {
			if (mov.getId() == database.getAllItems().get(i).getId()) {
				doubleMovie = true;
			}
		}
		return doubleMovie;
	}

	private void setupTV() {

		title = (TextView) findViewById(R.id.detailTitle);
		rating = (TextView) findViewById(R.id.detailRatingText);
		release_date = (TextView) findViewById(R.id.detailYearText);
		description = (TextView) findViewById(R.id.detailDescriptionText);
		genre = (TextView) findViewById(R.id.detailGenreText);

	}

	@Override
	public void onInitializationFailure(Provider arg0,
			YouTubeInitializationResult arg1) {
		Toast.makeText(this, "Sorry, Youtube Player failed to initialize!",
				Toast.LENGTH_LONG).show();

	}

	@Override
	public void onInitializationSuccess(Provider provider,
			YouTubePlayer player, boolean arg2) {

		if (mov.getYoutubeTrailerURL() != null) {
			player.loadVideo(mov.getYoutubeTrailerURL());
		}
	}

}