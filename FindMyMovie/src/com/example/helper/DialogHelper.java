package com.example.helper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class DialogHelper {

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

	public static void internetDialog(Context context) {
		AlertDialog internetDialog = DialogHelper.getErrorDialog(context,
				"Error", "Please check your internet connection!", true);

		internetDialog.show();
	}

	public static void errorDialog(Context context) {
		AlertDialog errorDialog = DialogHelper.getErrorDialog(context, "Error",
				"No movies were found!", true);

		errorDialog.show();
	}

}
