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

import fr.licpro.filebox.constants.MimeTypeEnum;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Data transfer object for a file. The file is indexed by his filename and his
 * hash. Folder are file (UNIX style).
 * 
 * @author julien, Skywodd
 */
public class FileboxFile extends FileboxDtoBase {

	/**
	 * Serialization UID.
	 */
	private static final long serialVersionUID = -1575868431460413408L;

	/**
	 * The filename.
	 */
	@JsonProperty("name")
	private String mFilename;

	/**
	 * The file hash.
	 */
	@JsonProperty("hashId")
	private String mFileHash;

	/**
	 * True if the file is a folder.
	 */
	@JsonProperty("isFolder")
	private Boolean mIsFolder;

	/**
	 * The file type.
	 */
	@JsonProperty("fileType")
	private MimeTypeEnum mFileType;

	/**
	 * The last modification date (UNIX time stamp).
	 */
	@JsonProperty("lastModification")
	private Date mLastModification;

	/**
	 * Default constructor of the FileboxFile class for the serialization
	 * processor.
	 */
	public FileboxFile() {
	}

	/**
	 * Complete constructor of the FileboxFile class for general purpose uses.
	 * 
	 * @param filename
	 *            The filename.
	 * @param fileHash
	 *            The file hash.
	 * @param isFolder
	 *            Set to true if the file is a folder false otherwise.
	 * @param mimeType
	 *            The file type.
	 * @param lastModification
	 *            The last modification date (UNIX time stamp).
	 */
	public FileboxFile(String filename, String fileHash, Boolean isFolder,
			MimeTypeEnum mimeType, Date lastModification) {
		mFilename = filename;
		mFileHash = fileHash;
		mIsFolder = isFolder;
		mFileType = mimeType;
		mLastModification = lastModification;
	}

	// TODO doc

	public String getFilename() {
		return mFilename;
	}

	public void setFilename(String filename) {
		mFilename = filename;
	}

	public String getFileHash() {
		return mFileHash;
	}

	public void setFileHash(String fileHash) {
		mFileHash = fileHash;
	}

	public Boolean isFolder() {
		return mIsFolder;
	}

	public void setIsFolder(Boolean isFolder) {
		mIsFolder = isFolder;
	}

	public MimeTypeEnum getFileType() {
		return mFileType;
	}

	public void setFileType(MimeTypeEnum fileType) {
		mFileType = fileType;
	}

	public Date getLastModificationDate() {
		return mLastModification;
	}

	public void setLastModificationDate(Date lastModification) {
		mLastModification = lastModification;
	}

}
