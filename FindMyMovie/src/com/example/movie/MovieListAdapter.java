package com.example.movie;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MovieListAdapter extends BaseAdapter {

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
		TextView title = (TextView) convertView.findViewById(R.id.title);
		TextView id = (TextView) convertView.findViewById(R.id.id);
		TextView rating = (TextView) convertView.findViewById(R.id.rating);
		title.setText(mov.getTitle().replaceAll("\'", "´"));
		id.setText("" + mov.getId());
		rating.setText("" + mov.getRating());
	}

}