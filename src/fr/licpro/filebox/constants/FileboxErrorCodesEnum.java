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
	BAD_REQUEST("40000", "bad.request", "Bad request"),

	/**
	 * Triggered when the specified user cannot be found in the user database.
	 */
	USER_NOT_FOUND("40401", "user.not.found", "User not found"),

	/**
	 * Triggered when the given authentication token cannot be validated by the
	 * server.
	 */
	TOKEN_INVALID("40402", "token.invalid", "Token invalid"),

	/**
	 * Triggered when the specified file handle cannot be found on the server
	 * files system.
	 */
	FILE_NOT_FOUND("40403", "file.not.found", "File not found"),

	/**
	 * Triggered when the given login already exist in the user database.
	 */
	LOGIN_ALREADY_EXIST("60001", "login.exist", "Login exist"),

	/**
	 * Triggered when an unknown error is detected during the request processing
	 * at server side.
	 */
	UNKNOWN_ERROR("50001", "unknown.error", "Unknown error");

	/**
	 * The error code.
	 */
	private final String mErrorCode;

	/**
	 * The error message key (machine-readable).
	 */
	private final String mErrorMessageKey;

	/**
	 * The error message (human-readable).
	 */
	private final String mErrorDefaultMessage;

	/**
	 * Constructor of an FileboxErrorCodesEnum element.
	 * 
	 * @param errorCode
	 *            The error code.
	 * @param errorMessageKey
	 *            The error message key (machine-readable).
	 * @param errorDefaultMessage
	 *            The error message (human-readable).
	 */
	private FileboxErrorCodesEnum(final String errorCode,
			final String errorMessageKey, final String errorDefaultMessage) {
		mErrorMessageKey = errorMessageKey;
		mErrorCode = errorCode;
		mErrorDefaultMessage = errorDefaultMessage;
	}

	/**
	 * Get the error code.
	 * 
	 * @return The error code.
	 */
	public String getErrorCode() {
		return mErrorCode;
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
	 * Get the error message (human-readable).
	 * 
	 * @return The error message (human-readable).
	 */
	public String getErrorDefaultMessage() {
		return mErrorDefaultMessage;
	}

}
