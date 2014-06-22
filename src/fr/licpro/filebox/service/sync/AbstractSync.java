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
import fr.licpro.filebox.service.IRestClient;
import fr.licpro.filebox.service.ISync;

/**
 * Abstract base class for all sync classes implementations. Make the world of
 * sync simpler and better.
 * 
 * @author julien, skywodd
 */
public abstract class AbstractSync<T> implements ISync {

	/**
	 * Serialization UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Result of the REST API call. Null if error.
	 */
	protected T mData = null;

	/**
	 * Current application context.
	 */
	protected transient Context mContext;

	/**
	 * Implement this method to perform your custom sync task.
	 * 
	 * Success and Error handling can be performed by overriding either
	 * onSuccess() or onError() functions.
	 * 
	 * @param restClient
	 *            The REST client instance.
	 * @return The sync data result, or null if error.
	 * @throws RetrofitError
	 *             on REST/HTTP error.
	 */
	protected abstract T execute(final IRestClient restClient)
			throws RetrofitError;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.licpro.filebox.service.ISync#execute(android.content.Context,
	 * fr.licpro.filebox.service.IRestClient)
	 */
	@Override
	public boolean execute(final Context context, final IRestClient restClient) {

		/* Store the context for later use */
		mContext = context;

		/* Catch RetroFit error */
		try {

			/* Request data */
			mData = execute(restClient);

			/* Call onSucess callback */
			onSuccess();

			/* No error */
			return true;

		} catch (RetrofitError e) {

			/* Catch error and call onError() callback */
			onError(e);

			/* Oops */
			return false;
		}
	}

	/**
	 * Callback method called when all goes right and data is ready to use.
	 */
	protected abstract void onSuccess();

	/**
	 * Callback method called when something goes wrong during the data request.
	 * 
	 * @param e
	 *            The raised exception from RetroFit.
	 */
	protected abstract void onError(Exception e);

}
