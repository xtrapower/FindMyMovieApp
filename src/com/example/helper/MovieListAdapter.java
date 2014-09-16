package com.example.helper;

import java.util.ArrayList;

import com.example.movie.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
		TextView release_date = (TextView) convertView.findViewById(R.id.release);
		TextView rating = (TextView) convertView.findViewById(R.id.rating);
		ImageView popular = (ImageView) convertView.findViewById(R.id.popular);
		
		title.setText(mov.getTitle().replaceAll("\'", "´"));
		release_date.setText(mov.getReleaseDate().toString());
		rating.setText("Bewertung: " + mov.getRating());
		
		if(mov.getPopularity()<0.15){
			popular.setImageResource(R.drawable.red);
		}
		else if(mov.getPopularity()>0.15 & mov.getPopularity()<0.60){
			popular.setImageResource(R.drawable.yellow);
		}
		else if(mov.getPopularity()>0.60){
			popular.setImageResource(R.drawable.green);
		}
	}

}