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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import fr.licpro.filebox.R;
import fr.licpro.filebox.constants.FileboxRuntimeConstants;

/**
 * Abstract base activity for all application activities. Display the option
 * menu and handle basic menu options.
 * 
 * @author skywodd
 */
public abstract class AbstractBaseActivity extends Activity implements
		FileboxRuntimeConstants {

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Log.i(LOGCAT_TAG, "AbstractBaseActivity::onCreateOptionsMenu()"); //$NON-NLS-1$

		/* Inflate the menu */
		getMenuInflater().inflate(R.menu.menu_main, menu);

		/* Menu exist */
		return true;
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	protected void setupActionBar() {

		/* Display the UP button */
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		/* Handle the option selection */
		switch (item.getItemId()) {

		case android.R.id.home: // UP button
			Log.i(LOGCAT_TAG,
					"AbstractBaseActivity::onOptionsItemSelected(home)"); //$NON-NLS-1$

			/* Return to the parent activity */
			// NavUtils.navigateUpFromSameTask(this);
			onBackPressed();
			break;

		case R.id.action_settings: // Settings view
			Log.i(LOGCAT_TAG,
					"AbstractBaseActivity::onOptionsItemSelected(action_settings)"); //$NON-NLS-1$

			/* Open the settings activity */
			startActivity(new Intent(this, SettingsActivity.class));
			break;

		case R.id.action_about: // About view
			Log.i(LOGCAT_TAG,
					"AbstractBaseActivity::onOptionsItemSelected(action_about)"); //$NON-NLS-1$

			/* Open the about activity */
			startActivity(new Intent(this, AboutActivity.class));
			break;

		default: // Default system action
			return super.onOptionsItemSelected(item);
		}

		/* Action handled */
		return true;
	}

}
