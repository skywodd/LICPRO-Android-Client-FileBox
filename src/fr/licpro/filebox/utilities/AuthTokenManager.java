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

package fr.licpro.filebox.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Authentication token manager class. Allow read and write tothe authentication
 * token in shared preferences.
 * 
 * @author skywodd
 */
public final class AuthTokenManager {

	/**
	 * Shared preferences key for the authentication token.
	 */
	public static final String PREF_KEY_AUTH_TOKEN = "fr.licpro.filebox.PREF_KEY_AUTH_TOKEN";

	/**
	 * Return true if the authentication token is set and ready to use.
	 * 
	 * @param context
	 *            The parent application context.
	 * @return True if the authentication token is set, false otherwise.
	 */
	public static boolean isAuthTokenSet(Context context) {

		/* Check if the authentication token is set */
		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(context);
		return sharedPrefs.contains(PREF_KEY_AUTH_TOKEN);
	}

	/**
	 * Store the given authentication token for later use. Warning: Overwrite
	 * any previously set token.
	 * 
	 * @param context
	 *            The parent application context.
	 * @param newAuthToken
	 *            The new authentication token to be stored.
	 */
	public static void storeAuthToken(Context context, String newAuthToken) {

		/* Store the authentication token */
		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = sharedPrefs.edit();
		editor.putString(PREF_KEY_AUTH_TOKEN, newAuthToken);
		editor.commit();
	}

	/**
	 * Delete any currently set authentication token.
	 * 
	 * @param context
	 *            The parent application context.
	 */
	public static void eraseAuthToken(Context context) {

		/* Erase the authentication token */
		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = sharedPrefs.edit();
		editor.remove(PREF_KEY_AUTH_TOKEN);
		editor.commit();
	}

	/**
	 * Return the current authentication token.
	 * 
	 * @param context
	 *            The parent application context.
	 * @return The current authentication token, or null if no token available.
	 */
	public static String getAuthToken(Context context) {

		/* Return the current authentication token */
		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(context);
		return sharedPrefs.getString(PREF_KEY_AUTH_TOKEN, null);
	}

}
