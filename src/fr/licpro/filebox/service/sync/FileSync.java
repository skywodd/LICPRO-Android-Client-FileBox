package fr.licpro.filebox.service.sync;

import retrofit.RetrofitError;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import fr.licpro.filebox.constants.FileboxRuntimeConstants;
import fr.licpro.filebox.dto.FileboxFilesArray;
import fr.licpro.filebox.service.IRestClient;

public class FileSync extends AbstractSync<FileboxFilesArray> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3868415809968877735L;

	/**
	 * 
	 */
	private String mHash;

	/**
	 * 
	 */
	private long mTimeStamp;
	
	/**
	 *   success value.
	 */
	public static final String GET_FILE_SUCCESS = "fr.licpro.filebox.service.sync.FileSync.FILESYNC_GET_FILE_SUCCESS";

	/**
	 *   error value.
	 */
	public static final String GET_FILE_ERROR = "fr.licpro.filebox.service.sync.FileSync.FILESYNC_GET_FILE_ERROR";

	/*
	 * Constructor.
	 */
	public FileSync(String pHash, long pTimeStamp) {
		mHash = pHash;
		mTimeStamp = pTimeStamp;
	}

	@Override
	protected FileboxFilesArray execute(IRestClient pRestClient)
			throws RetrofitError {
		String token = "";
		SharedPreferences prefs = mContext.getSharedPreferences("pref.name",
				Context.MODE_PRIVATE);
		if (prefs.contains(ConnectionSync.TOKEN_PREF)) {
			token = prefs.getString(ConnectionSync.TOKEN_PREF, "");

		}
		
		if (mHash != null && mHash != "" && token != "") {
			return pRestClient.getUserFilesInSubDirectory(token, mHash,
					mTimeStamp);
		} else {
			return pRestClient.getUserFiles(token, mTimeStamp);
		}
	}

	@Override
	protected void onSuccess() {

		Log.i("TRACE","onSuccess" );
	
		Intent it = new Intent(GET_FILE_SUCCESS);
		it.setPackage(FileboxRuntimeConstants.BROADCAST_FILTER);
		mContext.sendBroadcast(it);

	}

	@Override
	protected void onError(Exception e) {
		Log.i("TRACE","onError" );
		Intent it = new Intent(GET_FILE_ERROR);
		it.setPackage(FileboxRuntimeConstants.BROADCAST_FILTER);
		mContext.sendBroadcast(it);

	}

}
