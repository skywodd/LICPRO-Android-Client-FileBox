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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import fr.licpro.filebox.R;
import fr.licpro.filebox.constants.FileboxRuntimeConstants;
import fr.licpro.filebox.dialogs.DeleteAuthTokenConfirmationDialogFragment;
import fr.licpro.filebox.dialogs.DeleteAuthTokenConfirmationDialogFragment.OnDeleteAuthTokenConfirmationListener;
import fr.licpro.filebox.utilities.AuthTokenManager;

/**
 * Settings activity.
 * 
 * @author skywodd
 */
public class SettingsActivity extends Activity implements
		FileboxRuntimeConstants, OnClickListener,
		OnDeleteAuthTokenConfirmationListener {

	/**
	 * Remove account credentials button.
	 */
	private Button mRemoveAccountButton;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(LOGCAT_TAG, "SettingsActivity::onCreate()"); //$NON-NLS-1$

		/* Set content view */
		setContentView(R.layout.activity_settings);

		/* Get all widget instances */
		mRemoveAccountButton = (Button) findViewById(R.id.btn_remove_account_credentials);

		/* Display the UP button */
		getActionBar().setDisplayHomeAsUpEnabled(true);

		/* Attach onClick() of button */
		mRemoveAccountButton.setOnClickListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		Log.i(LOGCAT_TAG, "SettingsActivity::onClick()"); //$NON-NLS-1$

		/* Show the confirmation dialog */
		DeleteAuthTokenConfirmationDialogFragment
				.showDialog(getFragmentManager());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.licpro.filebox.dialogs.DeleteAuthTokenConfirmationDialogFragment.
	 * OnDeleteAuthTokenConfirmationListener
	 * #onDeleteAuthTokenConfirmed(fr.licpro
	 * .filebox.dialogs.DeleteAuthTokenConfirmationDialogFragment)
	 */
	@Override
	public void onDeleteAuthTokenConfirmed(
			DeleteAuthTokenConfirmationDialogFragment dialog) {

		/* Delete the authentication token */
		AuthTokenManager.eraseAuthToken(this);

		/* Close all activities and got to the login activity */
		Intent intent = new Intent(this, LoginActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
				| Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivity(intent);
	}

}
