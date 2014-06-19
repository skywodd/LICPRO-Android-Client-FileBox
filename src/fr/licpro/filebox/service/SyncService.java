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

package fr.licpro.filebox.service;

import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import fr.licpro.filebox.constants.FileboxRuntimeConstants;
import fr.licpro.filebox.service.json.JacksonConverter;
import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Background synchronization service. Synchronize files and folders to and from
 * the FileBox server on Intent request.
 * 
 * @author Skywodd
 */
public class SyncService extends IntentService implements
		FileboxRuntimeConstants {

	/**
	 * Data in the intent
	 */
	public static final String SYNC_CLASS_INTENT = "fr.licpro.filebox.syncData";

	/**
	 * API REST client instance.
	 */
	protected IRestClient mRestClient;

	/**
	 * Default constructor of the SyncService class.
	 */
	public SyncService() {
		super("SyncService");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.IntentService#onCreate()
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(LOGCAT_TAG, "SyncService::onCreate()");

		/* Build the REST adapter */
		RestAdapter restAdapter = new RestAdapter.Builder()
				.setConverter(new JacksonConverter())//.setErrorHandler(null)
				.setLog(new AndroidLog(LOGCAT_TAG))
				.setLogLevel(RestAdapter.LogLevel.BASIC).setEndpoint(API_URL)
				.build();

		/* Create the API REST client */
		mRestClient = restAdapter.create(IRestClient.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.IntentService#onHandleIntent(android.content.Intent)
	 */
	@Override
	protected void onHandleIntent(final Intent pIntent) {
		Log.i(LOGCAT_TAG, "SyncService::onHandleIntent()");

		/* Get the sync request */
		ISync sync = (ISync) pIntent.getSerializableExtra(SYNC_CLASS_INTENT);
		
		/* Execute the sync request */
		sync.execute(getApplicationContext(), mRestClient);
	}

}
