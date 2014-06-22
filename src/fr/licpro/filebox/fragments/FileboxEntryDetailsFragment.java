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

import java.io.Serializable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OrmLiteBaseFragment;

import fr.licpro.filebox.R;
import fr.licpro.filebox.models.FileboxEntryModel;
import fr.licpro.filebox.orm.DatabaseHelper;

/**
 * FileboxEntry details fragment. Display informations about a given
 * FileboxEntry.
 * 
 * @author Dimitri
 */
public class FileboxEntryDetailsFragment extends
		OrmLiteBaseFragment<DatabaseHelper> {

	/**
	 * File argument to get the file to display.
	 */
	public static final String EXTRA_FILEBOX_ENTRY = "file_detail_entry";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View fragmentView = inflater.inflate(R.layout.fragment_file_details, container,
				false);
		// Inflate the layout for this fragment
		Bundle bundle = getArguments();

		// here is your list array
		FileboxEntryModel file = (FileboxEntryModel) bundle
				.getSerializable(EXTRA_FILEBOX_ENTRY);

		View imgView = fragmentView.findViewById(R.id.img_file_mimetype);

		((ImageView) imgView).setImageResource(file.getFileType()
				.getThumbnailResourceId());
		View tvFileName = fragmentView.findViewById(R.id.tv_file_name);
		((TextView) tvFileName).setText(file.getFilename());

		View tvFileType = fragmentView.findViewById(R.id.tv_file_type);
		((TextView) tvFileType).setText(file.getFileType().getMimeType());

		View tvFileLastModifivation = fragmentView
				.findViewById(R.id.tv_file_last_modification);
		((TextView) tvFileLastModifivation).setText(file
				.getLastModificationDate().toString());

		return fragmentView;
	}

	public static FileboxEntryDetailsFragment newInstance(FileboxEntryModel file) {
		FileboxEntryDetailsFragment f = new FileboxEntryDetailsFragment();

		// Supply index input as an argument.
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_FILEBOX_ENTRY, (Serializable) file);
		f.setArguments(args);

		return f;
	}
}
