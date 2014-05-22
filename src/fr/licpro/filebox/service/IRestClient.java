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

import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Part;
import retrofit.http.Query;
import fr.licpro.filebox.dto.FileboxAuthToken;
import fr.licpro.filebox.dto.FileboxFilesArray;

/**
 * Web-service API interface declaration for the REST client engine (RetroFit).
 * 
 * @author Skywodd
 * @version 1.0
 */
public interface IRestClient {

	/**
	 * API routine - Get an authentication token for the specified user using
	 * the given login and password.
	 * 
	 * @param login
	 *            The user name.
	 * @param password
	 *            The user password.
	 * @return The authentication token, or an error code if any.
	 */
	@PUT("/customer/token")
	FileboxAuthToken getUserToken(@Query("login") String login,
			@Query("password") String password);

	/**
	 * API routine - Get the list of user files in the root directory.
	 * 
	 * @param token
	 *            The authentication token.
	 * @param timestamp
	 *            The last update time stamp (for changes detection).
	 * @return The list of files in the root directory, or an error code if any.
	 */
	@GET("/file/{token}")
	FileboxFilesArray getUserFiles(@Part("token") String token,
			@Query("date") long timestamp);

	/**
	 * API routine - Get the list of user files in the specified sub directory.
	 * 
	 * @param token
	 *            The authentication token.
	 * @param directoryHashId
	 *            The target directory hash.
	 * @param timestamp
	 *            The last update time stamp (for changes detection).
	 * @return The list of files in the specified sub directory, or an error
	 *         code if any.
	 */
	@GET("/file/{token}/{hashId}")
	FileboxFilesArray getUserFilesInSubDirectory(@Part("token") String token,
			@Part("hashId") String directoryHashId,
			@Query("date") long timestamp);

	/**
	 * API routine - Get the list of user files in the specified sub directory.
	 * 
	 * @param token
	 *            The authentication token.
	 * @param fileHashId
	 *            The target file hash.
	 * @param timestamp
	 *            The last update time stamp (for changes detection).
	 * @return The list of files in the specified sub directory, or an error
	 *         code if any.
	 */
	@GET("/file/{token}/{hashId}")
	Response getUserFileContent(@Part("token") String token,
			@Part("hashId") String fileHashId, @Query("date") long timestamp);

}
