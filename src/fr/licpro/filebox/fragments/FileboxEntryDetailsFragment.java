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

package fr.licpro.filebox.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OrmLiteBaseFragment;

import fr.licpro.filebox.R;
import fr.licpro.filebox.constants.FileboxRuntimeConstants;
import fr.licpro.filebox.models.FileboxEntryModel;

/**
 * FileboxEntry details fragment. Display informations about a given
 * FileboxEntry.
 * 
 * @author Dimitri
 */
public class FileboxEntryDetailsFragment extends OrmLiteBaseFragment implements
		FileboxRuntimeConstants {

	/**
	 * Intent extra for the target file entry to be displayed.
	 */
	public static final String EXTRA_FILEBOX_ENTRY = "fr.licpro.filebox.FILEBOX_ENTRY";

	/**
	 * Constructor of the FileboxEntryDetailsFragment class.
	 * 
	 * @param file
	 *            The filebox entry to be displayed.
	 * @return A FileboxEntryDetailsFragment displaying the given file entry.
	 */
	public static FileboxEntryDetailsFragment newInstance(FileboxEntryModel file) {

		/* Create a new empty fragment */
		FileboxEntryDetailsFragment f = new FileboxEntryDetailsFragment();

		/* Populate all required extra */
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_FILEBOX_ENTRY, file);
		f.setArguments(args);

		/* Return the populated fragment */
		return f;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater,
	 * android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i(LOGCAT_TAG, "FileboxEntryDetailsFragment::onCreateView()"); //$NON-NLS-1$

		/* Inflate the fragment layout */
		View view = inflater.inflate(R.layout.fragment_file_details, container,
				false);

		/* Return the inflated layout */
		return view;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Fragment#onActivityCreated(android.os.Bundle)
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.i(LOGCAT_TAG, "CommunicationsFragment::onActivityCreated()"); //$NON-NLS-1$

		/* Get the fragment layout */
		View view = getView();

		/* Get the extra bundle */
		Bundle bundle = getArguments();

		/* Get the filebox entry to be displayed */
		FileboxEntryModel file = (FileboxEntryModel) bundle
				.getSerializable(EXTRA_FILEBOX_ENTRY);

		/* Get all widgets instances */
		ImageView typeThumbnail = (ImageView) view
				.findViewById(R.id.img_file_mimetype);
		TextView filename = (TextView) view.findViewById(R.id.tv_file_name);
		TextView fileType = (TextView) view.findViewById(R.id.tv_file_type);
		TextView lastModificationDate = (TextView) view
				.findViewById(R.id.tv_file_last_modification);

		/* Populate the view */
		typeThumbnail.setImageResource(file.getFileType()
				.getThumbnailResourceId());
		filename.setText(file.getFilename());
		fileType.setText(file.getFileType().getMimeType());
		lastModificationDate.setText(file.getLastModificationDate().toString());
	}

}
