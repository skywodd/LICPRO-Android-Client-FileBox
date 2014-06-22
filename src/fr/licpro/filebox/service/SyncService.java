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

import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;
import android.app.IntentService;
import android.content.Intent;
import fr.licpro.filebox.constants.FileboxRuntimeConstants;
import fr.licpro.filebox.service.json.JacksonConverter;

/**
 * Background synchronization service. Synchronize files and folders to and from
 * the FileBox server on Intent request.
 * 
 * @author Skywodd
 */
public class SyncService extends IntentService implements ErrorHandler,
		FileboxRuntimeConstants {

	/**
	 * Intent extra for the sync class to be executed by the service.
	 */
	public static final String EXTRA_SYNC_CLASS = "fr.licpro.filebox.SYNC_CLASS";

	/**
	 * REST client instance.
	 */
	protected IRestClient mRestClient;

	/**
	 * Default constructor of the SyncService class.
	 */
	public SyncService() {
		super("FileboxSyncService");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.IntentService#onCreate()
	 */
	@Override
	public void onCreate() {
		super.onCreate();

		/* Build the REST adapter */
		RestAdapter restAdapter = new RestAdapter.Builder()
				.setConverter(new JacksonConverter()).setErrorHandler(this)
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
	protected void onHandleIntent(final Intent intent) {

		/* Get the sync request */
		ISync sync = (ISync) intent.getSerializableExtra(EXTRA_SYNC_CLASS);

		/* Execute the sync request */
		sync.execute(getApplicationContext(), mRestClient);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see retrofit.ErrorHandler#handleError(retrofit.RetrofitError)
	 */
	@Override
	public Throwable handleError(RetrofitError cause) {
		Response r = cause.getResponse();
		if (r != null && r.getStatus() == 401) {
			// return new UnauthorizedException(cause);
			// TODO
		}
		return cause;
	}

}
