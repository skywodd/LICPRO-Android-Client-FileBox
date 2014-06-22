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

package fr.licpro.filebox.activity.adapters;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import fr.licpro.filebox.R;
import fr.licpro.filebox.constants.MimeTypeEnum;
import fr.licpro.filebox.models.FileboxEntryModel;

/**
 * Array adapter for the FileBox entries list view.
 * 
 * @author skywodd
 */
public class FileboxEntryAdapter extends ArrayAdapter<FileboxEntryModel> {

	/**
	 * View holder container for a list item displaying a FileboxEntryModel.
	 * 
	 * @author skywodd
	 */
	public static class FileboxEntryModelViewHolder {

		/**
		 * FileboxEntryModel filename text view.
		 */
		public TextView filename;

		/**
		 * FileboxEntryModel type thumbnail image view.
		 */
		public ImageView mimetypeImg;
	}

	/**
	 * Parent context.
	 */
	private Context mContext;

	/**
	 * Item layout resource ID.
	 */
	private int mLayoutResourceId;

	/**
	 * Array of display-able FileboxEntryModel.
	 */
	private ArrayList<FileboxEntryModel> mData;

	/**
	 * Create a new array adapter to display a list of FileboxEntryModel.
	 * 
	 * @param context
	 *            The parent context.
	 * @param layoutResourceId
	 *            The item layout resource ID.
	 * @param data
	 *            The array of FileboxEntryModel to be displayed.
	 */
	public FileboxEntryAdapter(Context context, int layoutResourceId,
			ArrayList<FileboxEntryModel> data) {
		super(context, layoutResourceId, data);
		mLayoutResourceId = layoutResourceId;
		mContext = context;
		mData = data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		/* View holder container for the FileboxEntryModel list item */
		FileboxEntryModelViewHolder holder = null;

		/* Create a new view or recycle an old one. */
		if (convertView == null) {

			/* Inflate a new list item */
			LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
			convertView = inflater.inflate(mLayoutResourceId, parent, false);

			/* Create and populate a new view holder container */
			holder = new FileboxEntryModelViewHolder();
			holder.filename = (TextView) convertView
					.findViewById(R.id.tv_list_item_filename);
			holder.mimetypeImg = (ImageView) convertView
					.findViewById(R.id.img_list_item_thumbnail);

			/* Bind the container to the list item as tag */
			convertView.setTag(holder);

		} else {

			/* Recycle an old view */
			holder = (FileboxEntryModelViewHolder) convertView.getTag();
		}

		/* Get the item data */
		FileboxEntryModel entry = mData.get(position);

		/* Populate the view fields */
		holder.filename.setText(entry.getFilename());
		if (!entry.isFolder()) {

			/* Get the mimetype of the file */
			MimeTypeEnum mimetype = MimeTypeEnum.getType(entry.getFilename());
			int thumbnail = (mimetype != null) ? mimetype
					.getThumbnailResourceId() : R.drawable.ic_mimetype_unknown;

			/* Set the type thumbnail image */
			holder.mimetypeImg.setImageResource(thumbnail);

		} else {

			/* Display the directory thumbnail image */
			holder.mimetypeImg.setImageResource(R.drawable.ic_mimetype_folder);
		}

		/* Return the populated view */
		return convertView;
	}

	/**
	 * Replace the current data by a new set of data.
	 * 
	 * @param newData
	 *            The new data.
	 */
	public void setData(List<FileboxEntryModel> newData) {

		/* Clear all current data and store the new ones */
		clear();
		addAll(newData);
	}

}