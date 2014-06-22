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

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import fr.licpro.filebox.R;
import fr.licpro.filebox.fragments.FileboxEntryDetailsFragment;
import fr.licpro.filebox.models.FileboxEntryModel;

/**
 * File details activity.
 * 
 * @author skywodd
 */
public class FileDetailsActivity extends AbstractBaseActivity {

	/**
	 * Intent extra for the target file entry to be displayed.
	 */
	public static final String EXTRA_FILEBOX_ENTRY = "fr.licpro.filebox.FILEBOX_ENTRY";

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(LOGCAT_TAG, "FileDetailsActivity::onCreate()");

		/* Set content view */
		setContentView(R.layout.activity_file_details);

		/* Get the extra bundle */
		Bundle extra = getIntent().getExtras();

		/* Display the target file informations */
		FragmentManager fm = getFragmentManager();
		FileboxEntryDetailsFragment fragment = FileboxEntryDetailsFragment
				.newInstance((FileboxEntryModel) extra
						.getSerializable(EXTRA_FILEBOX_ENTRY));
		FragmentTransaction fragmentTransaction = fm.beginTransaction();
		fragmentTransaction.replace(android.R.id.content, fragment);
		fragmentTransaction.commit();
	}

}
