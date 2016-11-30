package com.yamankod.component_15_mycallexample;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog.Calls;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

public class CPExamplesActivity extends Activity {

	int PERMISSIONS_REQUEST_CODE = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		//permissions
		getPermissionReadContacts();
		getPermissionReadCallLog();
		getPermissionWriteCallLog();


		Uri allCalls = Uri.parse("content://call_log/calls");
		Cursor c = managedQuery(allCalls, null, null, null, null);
		if (c.moveToFirst()) {
			do {
				String callType = "";
				switch (Integer.parseInt(c.getString(c
						.getColumnIndex(Calls.TYPE)))) {
				case 1:
					callType = "Incoming";
					break;
				case 2:
					callType = "Outgoing";
					break;
				case 3:
					callType = "Missed";
				}
				Log.v("Content Providers",
						c.getString(c.getColumnIndex(Calls._ID)) + ", "
								+ c.getString(c.getColumnIndex(Calls.NUMBER))
								+ ", " + callType);
				Toast.makeText(getApplicationContext(), c.getString(c.getColumnIndex(Calls._ID)) + ", "
						+ c.getString(c.getColumnIndex(Calls.NUMBER))
						+ ", " + callType, 1).show();
			} while (c.moveToNext());
		}
	}

	/**
	 *
	 *
	 android:name="android.permission.READ_CONTACTS">
	 * Android  persmission for marshmallow
	 *
	 * gerekli izinler için aşagıda ki arama izmine benzer metodlar yazılıp çagırılmalıdır.
	 */

	public void getPermissionReadContacts() {
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
				!= PackageManager.PERMISSION_GRANTED) {


			if (shouldShowRequestPermissionRationale(
					Manifest.permission.READ_CONTACTS)) {

			}
			requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},
					PERMISSIONS_REQUEST_CODE);
		}
	}

	public void getPermissionReadCallLog() {
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)
				!= PackageManager.PERMISSION_GRANTED) {


			if (shouldShowRequestPermissionRationale(
					Manifest.permission.READ_CALL_LOG)) {

			}
			requestPermissions(new String[]{Manifest.permission.READ_CALL_LOG},
					PERMISSIONS_REQUEST_CODE);
		}
	}
	public void getPermissionWriteCallLog() {
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALL_LOG)
				!= PackageManager.PERMISSION_GRANTED) {


			if (shouldShowRequestPermissionRationale(
					Manifest.permission.WRITE_CALL_LOG)) {

			}
			requestPermissions(new String[]{Manifest.permission.WRITE_CALL_LOG},
					PERMISSIONS_REQUEST_CODE);
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
		if (requestCode == PERMISSIONS_REQUEST_CODE) {
			if (grantResults.length == 1 &&
					grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				Toast.makeText(this, "permission granted", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show();
			}
		} else {
			super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		}
	}

}

