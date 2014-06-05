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
import fr.licpro.filebox.dto.FileboxAuthToken;
import fr.licpro.filebox.service.IRestClient;

/**
 * Method to Sync the startUp data
 */
public class ConnectionSync extends AbstractSync<FileboxAuthToken> {

	/**
	 * Serialization UID.
	 */
	private static final long serialVersionUID = -3470928285752718451L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.licpro.filebox.service.sync.AbstractSync#execute(fr.licpro.filebox
	 * .service.IRestClient)
	 */
	@Override
	protected FileboxAuthToken execute(final IRestClient pRestClient)
			throws RetrofitError {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.licpro.filebox.service.sync.AbstractSync#onSuccess()
	 */
	@Override
	protected void onSuccess() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.licpro.filebox.service.sync.AbstractSync#onError(java.lang.Exception)
	 */
	@Override
	protected void onError(Exception e) {

	}

}
