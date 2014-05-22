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

/**
 * Data transfer object for the authentication token. This token is used to
 * authenticate users without transmitting user name and password at each API
 * request.
 * 
 * @author julien, Skywodd
 */
public class FileboxAuthToken extends FileboxServerError {

	/**
	 * Serialization UID.
	 */
	private static final long serialVersionUID = 3938403319024291969L;

	/**
	 * The authentication token.
	 */
	@JsonProperty("token")
	private String mToken;

	/**
	 * Default constructor of the FileboxAuthToken class for the serialization
	 * processor.
	 */
	public FileboxAuthToken() {
	}

	/**
	 * Complete constructor of the FileboxAuthToken class for general purpose
	 * uses.
	 * 
	 * @param token
	 *            The authentication token.
	 */
	public FileboxAuthToken(final String token) {
		mToken = token;
	}

	/**
	 * Get the authentication token.
	 * 
	 * @return The authentication token.
	 */
	public String getToken() {
		return mToken;
	}

	/**
	 * Set the authentication token.
	 * 
	 * @param token
	 *            The new authentication token.
	 */
	public void setToken(final String token) {
		mToken = token;
	}

}
