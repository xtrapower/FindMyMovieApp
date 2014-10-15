package com.example.activities;

import com.example.movie.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class StartActivity extends Activity {

	private Button searchButton, watchlistButton;
	private Intent intent;
	public static final String PREFS_NAME = "MyPrefsFile1";
	public CheckBox dontShowAgain;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupUI();
	}

	private void setupUI() {
		setContentView(R.layout.start_activity);
		setupTV();
		setupButtons();
		setupCheckBox();

	}

	private void setupCheckBox() {
		AlertDialog.Builder adb = new AlertDialog.Builder(this);
		LayoutInflater adbInflater = LayoutInflater.from(this);
		View eulaLayout = adbInflater.inflate(R.layout.checkbox, null);
		dontShowAgain = (CheckBox) eulaLayout.findViewById(R.id.skip);
		adb.setView(eulaLayout);
		adb.setTitle(R.string.attention);
		adb.setMessage(R.string.instruction);
		adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				String checkBoxResult = "NOT checked";
				if (dontShowAgain.isChecked())
					checkBoxResult = "checked";
				SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
				SharedPreferences.Editor editor = settings.edit();
				editor.putString("skipMessage", checkBoxResult);
				editor.commit();
				return;
			}
		});

		adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				String checkBoxResult = "NOT checked";
				if (dontShowAgain.isChecked())
					checkBoxResult = "checked";
				SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
				SharedPreferences.Editor editor = settings.edit();
				editor.putString("skipMessage", checkBoxResult);
				editor.commit();
				return;
			}
		});
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		String skipMessage = settings.getString("skipMessage", "NOT checked");
		if (!skipMessage.equals("checked"))
			adb.show();

	}

	private void setupButtons() {

		searchButton = (Button) findViewById(R.id.search);

		searchButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				intent = new Intent();
				intent.setClass(StartActivity.this, ChooseSearchActivity.class);
				startActivity(intent);
			}
		});
		watchlistButton = (Button) findViewById(R.id.watchlist);
		watchlistButton.setOnClickListener(new View.OnClickListener() {

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

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_help:
			intent = new Intent();
			intent.setClass(StartActivity.this, HelpActivity.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}

	}

}
