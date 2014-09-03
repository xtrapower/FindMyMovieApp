package com.example.helper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class DialogHelper {

	public static ProgressDialog getProgressDialog(Context context,
			String dialogTitle, String dialogText) {
		ProgressDialog progressDialog = new ProgressDialog(context);
		progressDialog.setTitle(dialogTitle);
		progressDialog.setMessage(dialogText);
		return progressDialog;
	}

	public static AlertDialog getErrorDialog(final Context context,
			String title, String msg, boolean cancelable) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);
		alertDialogBuilder.setTitle(title);
		alertDialogBuilder.setMessage(msg);
		alertDialogBuilder.setPositiveButton("OK", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				((Activity) context).finish();
			}
		});
		return alertDialogBuilder.create();

	}
}
