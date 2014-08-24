package com.example.movie;


import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;

public class DetailActivity extends Activity {


	Bitmap mIcon_val;
	URL newurl;
	MovieDatabase database;
	PopupWindow popUp;
	Button addButton;
	NotificationCompat.Builder mBuilder;
	DetailFetcher dF;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupUI();
		dF = new DetailFetcher();
		dF.execute(157);
	}

	private void setupUI() {
		setContentView(R.layout.detail);
		mBuilder = new NotificationCompat.Builder(this).setContentTitle("")
	    .setContentText("Film wurde der WatchList hinzugefügt!");
		setupTV();
		setupButton();
		initLocationDatabase();
		}

		private void setupButton() {
			
			addButton = (Button) findViewById(R.id.button1);
			addButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					//Movie mov = (Movie) list .getItemAtPosition(position);
					//database.insertData(mov);	
					
				}
			});
			  		
	}

		private void initLocationDatabase() {
			database = new MovieDatabase(this);
			database.open();
		
	}

	private void setupTV() {
		WebView web = (WebView) findViewById(R.id.webView1);
		web.loadUrl("http://image.tmdb.org/t/p/w150/f9iH7Javzxokvnkiz2yHD1dcmUy.jpg");
		
	}

}
