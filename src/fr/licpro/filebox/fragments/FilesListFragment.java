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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OrmLiteBaseFragment;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import fr.licpro.filebox.R;
import fr.licpro.filebox.activity.adapters.FileboxEntryAdapter;
import fr.licpro.filebox.constants.FileboxRuntimeConstants;
import fr.licpro.filebox.models.FileboxEntryModel;
import fr.licpro.filebox.service.SyncService;
import fr.licpro.filebox.service.sync.FileSync;

/**
 * Files list fragment. Display a list of FileboxEntry for the specified
 * directory.
 * 
 * @author skywodd
 */
public class FilesListFragment extends OrmLiteBaseFragment implements
		FileboxRuntimeConstants, OnItemClickListener {

	/**
	 * Fragment name.
	 */
	public static final String FRAGMENT_NAME = "FilesListFragment"; //$NON-NLS-1$

	/**
	 * Callback listener interface for the FilesList fragment.
	 * 
	 * @author Fabien Batteix
	 */
	public interface OnFileboxEntryClickListener {

		/**
		 * Called when the user click on a filebox entry. Sub directories are
		 * handled internally, only click on a real file trigger this callback.
		 * 
		 * @param dialog
		 *            The source dialog instance.
		 * @param entry
		 *            The filebox entry to be displayed.
		 */
		void onFileboxEntryClick(FilesListFragment dialog,
				FileboxEntryModel entry);

	}

	/**
	 * Callback for events handling.
	 */
	private OnFileboxEntryClickListener mCallback;

	/**
	 * Files list view.
	 */
	private ListView mFilesList;

	/**
	 * Array adapter for the list view.
	 */
	private FileboxEntryAdapter mAdapter;

	/**
	 * Files list header text view.
	 */
	private TextView mFilesListHeader;

	/**
	 * Currently displayed directory.
	 */
	private FileboxEntryModel mCurrentDirectory = null;

	/**
	 * Filebox entries modifications broadcast receiver.
	 */
	private FileboxEntriesBroadcastReceiver mFileboxEntriesBroadcastReceiver = new FileboxEntriesBroadcastReceiver();

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.DialogFragment#onAttach(android.app.Activity)
	 */
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.i(LOGCAT_TAG, "FilesListFragment::onAttach()"); //$NON-NLS-1$

		/* Verify that the host activity implements the callback interface */
		try {

			/*
			 * Store the OnFileboxEntryClickListener instance so we can send
			 * events to the host.
			 */
			mCallback = (OnFileboxEntryClickListener) activity;

		} catch (ClassCastException e) {

			/* The activity doesn't implement the interface, throw exception */
			throw new ClassCastException(activity.getClass().getName()
					+ " must implement OnFileboxEntryClickListener"); //$NON-NLS-1$
		}
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
		Log.i(LOGCAT_TAG, "FilesListFragment::onCreateView()"); //$NON-NLS-1$

		/* Inflate the fragment layout */
		View view = inflater.inflate(R.layout.fragment_files_list, container,
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

		/* Inflate the list header */
		View header = (View) getActivity().getLayoutInflater().inflate(
				R.layout.listview_header_row, null);

		/* Get all required view instances */
		mFilesList = (ListView) view.findViewById(R.id.lv_files_list);
		mFilesListHeader = (TextView) header
				.findViewById(R.id.tv_files_list_header);

		/* Setup the list */
		mAdapter = new FileboxEntryAdapter(getActivity(),
				R.layout.listview_item_row, new ArrayList<FileboxEntryModel>());
		mFilesList.addHeaderView(header);
		mFilesList.setAdapter(mAdapter);

		/* Handle list item click */
		mFilesList.setOnItemClickListener(this);

		/* Load root directory content */
		updateFileboxEntriesFromServer();
		reloadFileboxEntries();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Fragment#onResume()
	 */
	@Override
	public void onResume() {
		super.onResume();
		Log.i(LOGCAT_TAG, "CommunicationsFragment::onResume()");

		/* Register the broadcast receiver for the service callback */
		getActivity().registerReceiver(mFileboxEntriesBroadcastReceiver,
				new IntentFilter(FileSync.ACTION_SYNC_FILES_SUCCESS));
		getActivity().registerReceiver(mFileboxEntriesBroadcastReceiver,
				new IntentFilter(FileSync.ACTION_SYNC_FILES_ERROR));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Fragment#onPause()
	 */
	@Override
	public void onPause() {
		super.onPause();
		Log.i(LOGCAT_TAG, "CommunicationsFragment::onPause()");

		/* Unregister the broadcast receiver */
		getActivity().unregisterReceiver(mFileboxEntriesBroadcastReceiver);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.DialogFragment#onDetach()
	 */
	@Override
	public void onDetach() {
		super.onDetach();
		Log.i(LOGCAT_TAG, "FilesListFragment::onDetach()"); //$NON-NLS-1$

		/* Avoid leaking the activity instance */
		mCallback = null;
	}

	/**
	 * Request updated filebox entries list from server.
	 */
	private void updateFileboxEntriesFromServer() {

		/* Request files sync from the service */
		Intent intent = new Intent(getActivity(), SyncService.class);
		intent.putExtra(SyncService.EXTRA_SYNC_CLASS,
				new FileSync((mCurrentDirectory == null) ? null
						: mCurrentDirectory.getFileHash(), 0L));
		getActivity().startService(intent);
	}

	/**
	 * Load the content of the specified directory.
	 */
	private void reloadFileboxEntries() {

		/* Refresh the list header */
		mFilesListHeader.setText((mCurrentDirectory == null) ? "/"
				: mCurrentDirectory.getFilename());

		/* Get the FileboxEntryModel dao */
		RuntimeExceptionDao<FileboxEntryModel, Integer> fileboxEntryModelRuntimeDao = getHelper()
				.getFileboxEntryModelRuntimeDao();

		/* Create a new query builder for the filebox database */
		QueryBuilder<FileboxEntryModel, Integer> queryBuilder = fileboxEntryModelRuntimeDao
				.queryBuilder();

		/* Create a query to list all files in the current directory */
		try {
			if (mCurrentDirectory == null) {
				queryBuilder.where().isNull("parentHashId");
			} else {
				queryBuilder.where().eq("parentHashId",
						mCurrentDirectory.getFileHash());
			}
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}

		/* Prepare the sql statement */
		PreparedQuery<FileboxEntryModel> preparedQuery = null;
		try {
			preparedQuery = queryBuilder.prepare();
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}

		/* Query all files in the current directory */
		List<FileboxEntryModel> filesList = fileboxEntryModelRuntimeDao
				.query(preparedQuery);

		/* Update list data */
		mAdapter.setData(filesList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget
	 * .AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		// TODO check for directory -> change current dir and reload OR call
		// callback
	}

	/**
	 * Broadcast receiver for all filebox entries change notifications.
	 * 
	 * @author skywodd
	 */
	class FileboxEntriesBroadcastReceiver extends BroadcastReceiver {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * android.content.BroadcastReceiver#onReceive(android.content.Context,
		 * android.content.Intent)
		 */
		@Override
		public void onReceive(Context context, Intent intent) {
			Log.i(LOGCAT_TAG, "FileboxEntriesBroadcastReceiver::onReceive()");

			/* Get the intent action */
			String action = intent.getAction();

			/* Handle the action */
			if (action.equals(FileSync.ACTION_SYNC_FILES_SUCCESS)) {
				Log.i(LOGCAT_TAG,
						"FileboxEntriesBroadcastReceiver::onReceive(SYNC_FILES_SUCCESS)");

				/* Reload all filebox entries from database */
				reloadFileboxEntries();

			} else if (action.equals(FileSync.ACTION_SYNC_FILES_ERROR)) {
				Log.i(LOGCAT_TAG,
						"FileboxEntriesBroadcastReceiver::onReceive(SYNC_FILES_ERROR)");

				/* Display inline toast message */
				Crouton.showText(getActivity(),
						getString(R.string.error_sync_error), Style.ALERT);
			}
		}

	}

}
