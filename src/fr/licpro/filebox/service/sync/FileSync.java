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

package fr.licpro.filebox.service.sync;

import retrofit.RetrofitError;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import fr.licpro.filebox.constants.FileboxRuntimeConstants;
import fr.licpro.filebox.dto.FileboxFilesArray;
import fr.licpro.filebox.service.IRestClient;
import fr.licpro.filebox.utilities.AuthTokenManager;

/**
 * Sync class for a FileBox directory. Store the list of files in the specified
 * directory in the internal database of the application.
 * 
 * @author Dimitri
 */
public class FileSync extends AbstractSync<FileboxFilesArray> {

	/**
	 * Serialization UID.
	 */
	private static final long serialVersionUID = -3868415809968877735L;

	/**
	 * Intent action string for the sync files success broadcast.
	 */
	public static final String ACTION_SYNC_FILES_SUCCESS = "fr.licpro.filebox.SYNC_FILES_SUCCESS";

	/**
	 * Intent action string for the sync files error broadcast.
	 */
	public static final String ACTION_SYNC_FILES_ERROR = "fr.licpro.filebox.SYNC_FILES_ERROR";

	/**
	 * Target directory hash ID.
	 */
	private String mTargetDirectoryHashId;

	/**
	 * Only sync modified files after the given time stamp.
	 */
	private long mSyncFromTimeStamp;

	/**
	 * Constructor of FileSync the class.
	 * 
	 * @param targetDirectoryHashId
	 *            Target directory hash ID.
	 * @param syncFromTimeStamp
	 *            Only sync modified files after the given time stamp.
	 */
	public FileSync(String targetDirectoryHashId, long syncFromTimeStamp) {
		mTargetDirectoryHashId = targetDirectoryHashId;
		mSyncFromTimeStamp = syncFromTimeStamp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.licpro.filebox.service.sync.AbstractSync#execute(fr.licpro.filebox
	 * .service.IRestClient)
	 */
	@Override
	protected FileboxFilesArray execute(IRestClient restClient)
			throws RetrofitError {

		/* Get the current authentication token */
		String authToken = AuthTokenManager.getAuthToken(mContext);

		// TODO throw exception when authToken == null

		/* Call the adequate API routine based on target directory hash ID */
		if (mTargetDirectoryHashId != null) {

			/* Get content of a sub directory */
			return restClient.getUserFilesInSubDirectory(authToken,
					mTargetDirectoryHashId, mSyncFromTimeStamp);
		} else {

			/* Get content of the root directory */
			return restClient.getUserFiles(authToken, mSyncFromTimeStamp);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.licpro.filebox.service.sync.AbstractSync#onSuccess()
	 */
	@Override
	protected void onSuccess() {

		/* Store all files in the database */
		// TODO

		/* Broadcast SYNC_FILES_SUCCESS event */
		Intent intent = new Intent(ACTION_SYNC_FILES_SUCCESS);
		intent.setPackage(FileboxRuntimeConstants.BROADCAST_FILTER);
		mContext.sendBroadcast(intent);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.licpro.filebox.service.sync.AbstractSync#onError(java.lang.Exception)
	 */
	@Override
	protected void onError(Exception e) {

		/* Broadcast SYNC_FILES_ERROR event */
		Intent intent = new Intent(ACTION_SYNC_FILES_ERROR);
		// TODO add error code/message as extra
		intent.setPackage(FileboxRuntimeConstants.BROADCAST_FILTER);
		mContext.sendBroadcast(intent);
	}

}
