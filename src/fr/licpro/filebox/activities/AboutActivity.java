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

import fr.licpro.filebox.R;
import fr.licpro.filebox.constants.FileboxRuntimeConstants;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * About activity.
 * 
 * @author skywodd
 */
public class AboutActivity extends Activity implements FileboxRuntimeConstants {

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
		setContentView(R.layout.activity_about);

		/* Display the UP button */
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

}
