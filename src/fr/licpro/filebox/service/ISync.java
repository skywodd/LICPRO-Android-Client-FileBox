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

import java.io.Serializable;

import android.content.Context;

/**
 * Common API definition for all classes used to sync objects between the REST
 * service and the application.
 * 
 * @author julien, skywodd
 */
public interface ISync extends Serializable {

	/**
	 * Method used to execute the sync request.
	 * 
	 * @param context
	 *            The parent application context.
	 * @param restClient
	 *            The REST client instance.
	 * @return True on success, false on error.
	 */
	boolean execute(final Context context, final IRestClient restClient);

}
