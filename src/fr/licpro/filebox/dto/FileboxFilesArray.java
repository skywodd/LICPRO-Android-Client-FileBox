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

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Data transfer object for a directory. A directory is composed of a list of
 * files and a time stamp used to detect files modifications between two files
 * list refresh.
 * 
 * @author julien, Skywodd
 */
public class FileboxFilesArray extends FileboxServerError {

	/**
	 * Serialization UID.
	 */
	private static final long serialVersionUID = -8240730574273188468L;

	/**
	 * The list of files in this directory.
	 */
	@JsonProperty("listFile")
	private List<FileboxFile> mFiles;

	/**
	 * The last update time stamp of the list.
	 */
	@JsonProperty("lastUpdate")
	private Date mLastUpdate;

	/**
	 * Default constructor of the FileboxFilesArray class for the serialization
	 * processor.
	 */
	public FileboxFilesArray() {
	}

	/**
	 * Complete constructor of the FileboxFilesArray class for general purpose
	 * uses.
	 * 
	 * @param files
	 *            The list of files in this directory.
	 * @param lastUpdate
	 *            The last update date of this directory.
	 */
	public FileboxFilesArray(List<FileboxFile> files, Date lastUpdate) {
		mFiles = files;
		mLastUpdate = lastUpdate;
	}

	/**
	 * Get the list of files in this directory.
	 * 
	 * @return The list of files in this directory.
	 */
	public List<FileboxFile> getFilesList() {
		return mFiles;
	}

	/**
	 * Get the last update date of this directory.
	 * 
	 * @return The last update date of this directory.
	 */
	public Date getLastUpdateDate() {
		return mLastUpdate;
	}

}
