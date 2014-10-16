package com.example.helper;

import java.util.ArrayList;

import com.example.movie.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieListAdapter extends BaseAdapter {

	private double POPULARITY_LOW = 0.15;
	private double POPULARITY_HIGH = 0.6;

	private Context context;
	private ArrayList<Movie> movies;
	public Movie mov;

	public MovieListAdapter(Context context, ArrayList<Movie> movies) {
		this.context = context;
		this.movies = movies;

	}

	@Override
	public int getCount() {
		return movies.size();
	}

	@Override
	public Object getItem(int position) {
		return movies.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.movie_list_item, null);
		}
		mov = (Movie) getItem(position);
		setupTextView(convertView);

		return convertView;
	}

	private void setupTextView(View convertView) {

		WebView smallCover = (WebView) convertView
				.findViewById(R.id.cover_small);
		smallCover.setClickable(false);
		smallCover.setLongClickable(false);
		smallCover.setFocusable(false);
		smallCover.setFocusableInTouchMode(false);

		TextView title = (TextView) convertView.findViewById(R.id.title);
		TextView release_date = (TextView) convertView
				.findViewById(R.id.release);
		TextView rating = (TextView) convertView.findViewById(R.id.rating);

		ImageView popular = (ImageView) convertView.findViewById(R.id.popular);

		title.setText(mov.getTitle().replaceAll("\'", "´"));
		title.setTextColor(Color.WHITE);

		release_date.setText(mov.getReleaseDate());
		release_date.setTextColor(Color.WHITE);

		if (mov.getRating() == 0.0) {
			rating.setText("?");
			rating.setTextColor(Color.WHITE);
		} else {
			rating.setText(" " + mov.getRating());
			rating.setTextColor(Color.WHITE);
		}

		smallCover.loadUrl("https://image.tmdb.org/t/p/w92"
				+ mov.getImagePath());

		if (mov.getPopularity() == 0.0) {
			popular.setImageResource(R.drawable.ic_action_help);
		} else if (mov.getPopularity() > 0.0
				& mov.getPopularity() < POPULARITY_LOW) {
			popular.setImageResource(R.drawable.red);
		} else if (mov.getPopularity() > POPULARITY_LOW
				& mov.getPopularity() < POPULARITY_HIGH) {
			popular.setImageResource(R.drawable.yellow);
		} else if (mov.getPopularity() > POPULARITY_HIGH) {
			popular.setImageResource(R.drawable.green);
		}

	}

}