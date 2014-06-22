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

package fr.licpro.filebox.constants;

import org.apache.commons.lang3.StringUtils;

import android.content.Context;
import fr.licpro.filebox.R;

/**
 * Enumeration of all possible error codes from the FileBox server. Each error
 * code is composed of an integer error code, an error message (human-readable)
 * and an error message key (machine-readable).
 * 
 * @author julien, Skywodd
 */
public enum FileboxErrorCodesEnum {

	/**
	 * Triggered when a bad request is received and no processing of this
	 * request is possible.
	 */
	BAD_REQUEST(40000, "bad.request", R.string.error_bad_request),

	/**
	 * Triggered when the specified user cannot be found in the user database.
	 */
	USER_NOT_FOUND(40401, "user.not.found", R.string.error_user_not_found),

	/**
	 * Triggered when the given authentication token cannot be validated by the
	 * server.
	 */
	TOKEN_INVALID(40402, "token.invalid", R.string.error_token_invalid),

	/**
	 * Triggered when the specified file handle cannot be found on the server
	 * files system.
	 */
	FILE_NOT_FOUND(40403, "file.not.found", R.string.error_file_not_found),

	/**
	 * Triggered when the given login already exist in the user database.
	 */
	LOGIN_ALREADY_EXIST(60001, "login.exist", R.string.error_login_exist),

	/**
	 * Triggered when an unknown error is detected during the request processing
	 * at server side.
	 */
	UNKNOWN_ERROR(50001, "unknown.error", R.string.error_unknown_error);

	/**
	 * The error code.
	 */
	private final int mErrorCode;

	/**
	 * The error message key (machine-readable).
	 */
	private final String mErrorMessageKey;

	/**
	 * The error message resource ID (human-readable).
	 */
	private final int mErrorMessageResId;

	/**
	 * Constructor of an FileboxErrorCodesEnum element.
	 * 
	 * @param errorCode
	 *            The error code.
	 * @param errorMessageKey
	 *            The error message key (machine-readable).
	 * @param errorDefaultMessage
	 *            The error message resource ID (human-readable).
	 */
	private FileboxErrorCodesEnum(final int errorCode,
			final String errorMessageKey, final int errorMessageResId) {
		mErrorMessageKey = errorMessageKey;
		mErrorCode = errorCode;
		mErrorMessageResId = errorMessageResId;
	}

	/**
	 * Get the error code.
	 * 
	 * @return The error code.
	 */
	public int getErrorCode() {
		return mErrorCode;
	}

	/**
	 * Get the FileboxErrorCodesEnum for the specified error code.
	 * 
	 * @param errorCode
	 *            The error code to search for.
	 * @return The FileboxErrorCodesEnum associated with the given error code,
	 *         or null if not found.
	 */
	public static FileboxErrorCodesEnum getError(int errorCode) {

		/* Error unknown by default */
		FileboxErrorCodesEnum result = null;

		/* For each known error */
		for (FileboxErrorCodesEnum type : FileboxErrorCodesEnum.values()) {

			/* If the error code matches */
			if (type.mErrorCode == errorCode) {

				/* Store the result */
				result = type;
			}
		}

		/* Return the result */
		return result;
	}

	/**
	 * Get the error message key (machine-readable).
	 * 
	 * @return The error message key (machine-readable).
	 */
	public String getErrorMessageKey() {
		return mErrorMessageKey;
	}

	/**
	 * Get the FileboxErrorCodesEnum for the specified error message key.
	 * 
	 * @param errorMessageKey
	 *            The error message key to search for.
	 * @return The FileboxErrorCodesEnum associated with the given error message
	 *         key, or null if not found.
	 */
	public static FileboxErrorCodesEnum getError(String errorMessageKey) {

		/* Error unknown by default */
		FileboxErrorCodesEnum result = null;

		/*
		 * If the error message key is not blank (not null, not empty, not
		 * whitespace-only)
		 */
		if (StringUtils.isNotBlank(errorMessageKey)) {

			/* For each known error */
			for (FileboxErrorCodesEnum type : FileboxErrorCodesEnum.values()) {

				/* If the extensions matches */
				if (type.mErrorMessageKey.equalsIgnoreCase(errorMessageKey)) {

					/* Store the result */
					result = type;
				}
			}
		}

		/* Return the result */
		return result;
	}

	/**
	 * Get the error message (human-readable and localized).
	 * 
	 * @return The error message (human-readable and localized).
	 */
	public String getErrorMessage(Context context) {
		return context.getString(mErrorMessageResId);
	}

	/**
	 * Get the error message resource ID (human-readable).
	 * 
	 * @return The error message resource ID (human-readable).
	 */
	public int getErrorMessageResourceID() {
		return mErrorMessageResId;
	}

}
