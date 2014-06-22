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

package fr.licpro.filebox.dto;

import org.codehaus.jackson.annotate.JsonProperty;

import fr.licpro.filebox.constants.FileboxErrorCodesEnum;

/**
 * Data transfer object for the server answer in case of error during request
 * processing. High-level base class for all other DTO objects with possible
 * runtime errors.
 * 
 * @author julien, Skywodd
 */
public abstract class FileboxServerError extends FileboxDtoBase {

	/**
	 * Serialization UID.
	 */
	private static final long serialVersionUID = -197037154133716185L;

	/**
	 * The error code.
	 */
	@JsonProperty("errorCode")
	private String mErrorCode;

	/**
	 * The error message key (machine-readable).
	 */
	@JsonProperty("errorMessageKey")
	private String mErrorMessageKey;

	/**
	 * The default error message (human-readable).
	 */
	@JsonProperty("errorDefaultMessage")
	private String mDefaultErrorMessage;

	/**
	 * Default constructor of the FileboxServerError class for the serialization
	 * processor.
	 */
	public FileboxServerError() {
	}

	/**
	 * Complete constructor of the FileboxServerError class for general purpose
	 * uses.
	 * 
	 * @param errorCode
	 *            The error code.
	 * @param errorMessageKey
	 *            The error message key (machine-readable).
	 * @param defaultErrorMessage
	 *            The default error message (human-readable).
	 */
	public FileboxServerError(final int errorCode,
			final String errorMessageKey, final String defaultErrorMessage) {
		mErrorCode = Integer.toString(errorCode);
		mErrorMessageKey = errorMessageKey;
		mDefaultErrorMessage = defaultErrorMessage;
	}

	/**
	 * Get the error code.
	 * 
	 * @return The error code.
	 */
	public int getErrorCode() {
		return Integer.parseInt(mErrorCode);
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
	public String getDefaultErrorMessage() {
		return mDefaultErrorMessage;
	}

	/**
	 * Return the error as a FileboxErrorCodesEnum with localized error message
	 * string support.
	 * 
	 * @return The error as a FileboxErrorCodesEnum, or null if the error is
	 *         unknown.
	 */
	public FileboxErrorCodesEnum getErrorAsEnum() {
		return FileboxErrorCodesEnum.getError(mErrorMessageKey);
	}

}
