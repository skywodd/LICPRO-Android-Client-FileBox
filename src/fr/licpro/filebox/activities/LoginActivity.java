/*
 * This file is part of LICPRO-Android-Client-FileBox.
 *
 * LICPRO-Android-Client-FileBox is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * LICPRO-Android-Client-FileBox is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with LICPRO-Android-Client-FileBox.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.licpro.filebox.activities;

import org.apache.commons.lang3.StringUtils;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import fr.licpro.filebox.R;
import fr.licpro.filebox.constants.FileboxRuntimeConstants;
import fr.licpro.filebox.service.SyncService;
import fr.licpro.filebox.service.sync.ConnectionSync;
import fr.licpro.filebox.utilities.AuthTokenManager;

/**
 * Login activity.
 * 
 * @author Dimitri, skywodd
 */
public class LoginActivity extends Activity implements OnClickListener,
		FileboxRuntimeConstants {

	/**
	 * User name text field instance.
	 */
	private EditText mUsernameField;

	/**
	 * Password text field instance.
	 */
	private EditText mPasswordField;

	/**
	 * Login button.
	 */
	private Button mDoLoginButton;

	/**
	 * Login in progress animation.
	 */
	private ProgressBar mLoginProgressAnimation;

	/**
	 * Authentication token broadcast receiver
	 */
	private AuthTokenBroadcastReceiver mAuthTokenBroadcastReceiver = new AuthTokenBroadcastReceiver();

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(LOGCAT_TAG, "LoginActivity::onCreate()");

		/* Set the content view */
		setContentView(R.layout.activity_login);

		/* Get all widget instances */
		mUsernameField = (EditText) findViewById(R.id.tf_login_username);
		mPasswordField = (EditText) findViewById(R.id.tf_login_password);
		mDoLoginButton = (Button) findViewById(R.id.btn_do_login);
		mLoginProgressAnimation = (ProgressBar) findViewById(R.id.pb_login_progress);

		/* Handle do login button click */
		mDoLoginButton.setOnClickListener(this);

		/* Check for previous authentication token */
		if (AuthTokenManager.isAuthTokenSet(this)) {
			forwardToFilesListActivity();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
		Log.i(LOGCAT_TAG, "LoginActivity::onResume()");

		/* Register the broadcast receiver for the service callback */
		registerReceiver(mAuthTokenBroadcastReceiver, new IntentFilter(
				ConnectionSync.ACTION_CONNECTION_SUCCESS));
		registerReceiver(mAuthTokenBroadcastReceiver, new IntentFilter(
				ConnectionSync.ACTION_CONNECTION_ERROR));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		super.onPause();
		Log.i(LOGCAT_TAG, "LoginActivity::onPause()");

		/* Unregister the broadcast receiver */
		unregisterReceiver(mAuthTokenBroadcastReceiver);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		Log.i(LOGCAT_TAG, "LoginActivity::onClick()");

		/* Check user name field */
		String username = mUsernameField.getText().toString();
		if (StringUtils.isBlank(username)) {
			Log.i(LOGCAT_TAG, "LoginActivity::onClick(username blank)");
			mUsernameField.setError(getString(R.string.error_username_blank));
			return;
		}
		mUsernameField.setError(null);

		/* Check password field */
		String password = mPasswordField.getText().toString();
		if (StringUtils.isBlank(password)) {
			Log.i(LOGCAT_TAG, "LoginActivity::onClick(password blank)");
			mPasswordField.setError(getString(R.string.error_password_blank));
			return;
		}
		mPasswordField.setError(null);

		/* Show the login in progress view */
		showLoginInProgress();

		/* Start sync service and queue connection sync operation */
		Intent connectionService = new Intent(this, SyncService.class);
		connectionService.putExtra(SyncService.EXTRA_SYNC_CLASS,
				new ConnectionSync(username, password));
		startService(connectionService);
	}

	/**
	 * Show the login in progress view.
	 */
	private void showLoginInProgress() {

		/* Clear all toast messages */
		Crouton.cancelAllCroutons();

		/* Display inline toast message */
		Crouton.showText(this, getString(R.string.msg_login_in_progress),
				Style.INFO);

		/* Display waiting animation */
		mDoLoginButton.setVisibility(View.GONE);
		mLoginProgressAnimation.setVisibility(View.VISIBLE);
	}

	/**
	 * Show the standard login view.
	 */
	private void showLoginFormControls(String errorMessage) {

		/* Clear all toast messages */
		Crouton.cancelAllCroutons();

		/* Display error message if necessary */
		if (errorMessage != null) {

			/* Display inline toast message */
			Crouton.showText(this, errorMessage, Style.ALERT);
		}

		/* Display do login button */
		mDoLoginButton.setVisibility(View.VISIBLE);
		mLoginProgressAnimation.setVisibility(View.GONE);
	}

	/**
	 * Forward user to the files list activity.
	 */
	private void forwardToFilesListActivity() {
		Log.i(LOGCAT_TAG, "LoginActivity::forwardToFilesListActivity()");

		/* Open the files list activity according to the real screen width */
		DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
		float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
		if (dpWidth >= 480) {
			//startActivity(new Intent(this, FilesListWithDetailsActivity.class));
			startActivity(new Intent(this, FilesListActivity.class));
		} else {
			startActivity(new Intent(this, FilesListActivity.class));
		}
		
		/* Close the login screen */
		finish();
	}

	/**
	 * Authentication token broadcast receiver class.
	 * 
	 * @author Dimitri, skywodd
	 */
	class AuthTokenBroadcastReceiver extends BroadcastReceiver {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * android.content.BroadcastReceiver#onReceive(android.content.Context,
		 * android.content.Intent)
		 */
		@Override
		public void onReceive(Context context, Intent intent) {
			Log.i(LOGCAT_TAG, "AuthTokenBroadcastReceiver::onReceive()");

			/* Get the intent action */
			String action = intent.getAction();

			/* Handle the action */
			if (action.equals(ConnectionSync.ACTION_CONNECTION_SUCCESS)) {
				Log.i(LOGCAT_TAG,
						"AuthTokenBroadcastReceiver::onReceive(CONNECTION_SUCCESS)");

				/* Go to the files list activity */
				forwardToFilesListActivity();

			} else if (action.equals(ConnectionSync.ACTION_CONNECTION_ERROR)) {
				Log.i(LOGCAT_TAG,
						"AuthTokenBroadcastReceiver::onReceive(CONNECTION_ERROR)");

				/* Fall back to the login form with error message */
				showLoginFormControls(getString(R.string.msg_login_failed));
			}
		}

	}

}
