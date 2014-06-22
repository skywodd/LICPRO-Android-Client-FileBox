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
import android.content.Intent;
import fr.licpro.filebox.constants.FileboxRuntimeConstants;
import fr.licpro.filebox.dto.FileboxAuthToken;
import fr.licpro.filebox.service.IRestClient;
import fr.licpro.filebox.utilities.AuthTokenManager;

/**
 * Sync class for the authentication token. Store the authentication token in
 * the shared preferences of the application.
 * 
 * @author Dimitri
 */
public class ConnectionSync extends AbstractSync<FileboxAuthToken> {

	/**
	 * Serialization UID.
	 */
	private static final long serialVersionUID = -3470928285752718451L;

	/**
	 * Intent action string for the connection success broadcast.
	 */
	public static final String ACTION_CONNECTION_SUCCESS = "fr.licpro.filebox.CONNECTION_SUCCESS";

	/**
	 * Intent action string for the connection error broadcast.
	 */
	public static final String ACTION_CONNECTION_ERROR = "fr.licpro.filebox.CONNECTION_ERROR";

	/**
	 * The user Login.
	 */
	private String mUserLogin;

	/**
	 * The user Password.
	 */
	private String mUserPassword;

	/**
	 * Constructor of the ConnectionSync class.
	 * 
	 * @param userLogin
	 *            The user login.
	 * @param userPassword
	 *            The user password.
	 */
	public ConnectionSync(String userLogin, String userPassword) {
		mUserLogin = userLogin;
		mUserPassword = userPassword;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.licpro.filebox.service.sync.AbstractSync#execute(fr.licpro.filebox
	 * .service.IRestClient)
	 */
	@Override
	protected FileboxAuthToken execute(final IRestClient restClient)
			throws RetrofitError {
		return restClient.getUserToken(mUserLogin, mUserPassword);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.licpro.filebox.service.sync.AbstractSync#onSuccess()
	 */
	@Override
	protected void onSuccess() {

		/* Store the authentication token */
		AuthTokenManager.storeAuthToken(mContext, mData.getToken());

		/* Broadcast CONNECTION_SUCCESS event */
		Intent intent = new Intent(ACTION_CONNECTION_SUCCESS);
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

		/* Broadcast CONNECTION_ERROR event */
		Intent intent = new Intent(ACTION_CONNECTION_ERROR);
		// TODO add error code/message as extra
		intent.setPackage(FileboxRuntimeConstants.BROADCAST_FILTER);
		mContext.sendBroadcast(intent);
	}

}
