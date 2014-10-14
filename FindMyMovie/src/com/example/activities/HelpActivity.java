package com.example.activities;

import com.example.movie.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HelpActivity extends Activity {

	@SuppressWarnings("unused")
	private TextView helpText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupUI();
	}

	private void setupUI() {
		setContentView(R.layout.help);
		setupTV();

	}

	@SuppressWarnings("unused")
	private void setupTV() {

		TextView helpText = (TextView) findViewById(R.id.helpText);
	}

}
