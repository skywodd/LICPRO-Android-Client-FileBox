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
	 * The error message (human-readable).
	 */
	@JsonProperty("errorDefaultMessage")
	private String mErrorDefaultMessage;

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
	 * @param error
	 *            The error code as an FileboxErrorCodesEnum object.
	 */
	public FileboxServerError(final FileboxErrorCodesEnum error) {
		this(error.getErrorCode(), error.getErrorDefaultMessage(), error
				.getErrorMessageKey());
	}

	/**
	 * Complete constructor of the FileboxServerError class for general purpose
	 * uses.
	 * 
	 * @param errorCode
	 *            The error code.
	 * @param errorMessageKey
	 *            The error message key (machine-readable).
	 * @param errorDefaultMessage
	 *            The error message (human-readable).
	 */
	public FileboxServerError(final String errorCode,
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
	 * Set the error code.
	 * 
	 * @param errorCode
	 *            The new error code.
	 */
	public void setErrorCode(String errorCode) {
		mErrorCode = errorCode;
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
	 * Set the error message key (machine-readable).
	 * 
	 * @param errorMessageKey
	 *            The error message key (machine-readable).
	 */
	public void setErrorMessageKey(String errorMessageKey) {
		mErrorMessageKey = errorMessageKey;
	}

	/**
	 * Get the error message (human-readable).
	 * 
	 * @return The error message (human-readable).
	 */
	public String getErrorDefaultMessage() {
		return mErrorDefaultMessage;
	}

	/**
	 * Set the error message (human-readable).
	 * 
	 * @param errorDefaultMessage
	 *            The error message (human-readable).
	 */
	public void setErrorDefaultMessage(String errorDefaultMessage) {
		mErrorDefaultMessage = errorDefaultMessage;
	}

}
