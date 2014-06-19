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

package fr.licpro.filebox.models;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import fr.licpro.filebox.constants.MimeTypeEnum;

/**
 * This class is an ORMLite-able container for a filebox entry (file or
 * directory) informations.
 * 
 * @author Fabien Batteix
 */
@DatabaseTable(tableName = "filebox_entries")
public class FileboxEntryModel {

	/** The Filebox entries ID */
	@DatabaseField(generatedId = true, columnName = "id")
	private long mId;

	/** The parent Filebox entry ID, or nul if no parent (root directory) */
	@DatabaseField(foreign = true, foreignColumnName = "id", canBeNull = true, columnName = "parentId")
	private FileboxEntryModel mParent;

	/**
	 * The filename.
	 */
	@DatabaseField(columnName = "name")
	private String mFilename;

	/**
	 * The file hash.
	 */
	@DatabaseField(columnName = "hashId")
	private String mFileHash;

	/**
	 * True if the file is a folder.
	 */
	@DatabaseField(columnName = "isFolder")
	private boolean mIsFolder;

	/**
	 * The file type.
	 */
	@DatabaseField(columnName = "fileType", canBeNull = true)
	private MimeTypeEnum mFileType;

	/**
	 * The last modification date (UNIX time stamp).
	 */
	@DatabaseField(columnName = "lastModification")
	private Date mLastModification;

	/**
	 * Default constructor of the FileboxEntryModel class for the serialization
	 * processor.
	 */
	public FileboxEntryModel() {
	}

	/**
	 * Complete constructor of the FileboxEntryModel class for general purpose
	 * uses.
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
	public FileboxEntryModel(FileboxEntryModel parent, String filename,
			String fileHash, Boolean isFolder, MimeTypeEnum mimeType,
			Date lastModification) {
		mParent = parent;
		mFilename = filename;
		mFileHash = fileHash;
		mIsFolder = isFolder;
		mFileType = mimeType;
		mLastModification = lastModification;
	}

	// TODO doc

	public long getId() {
		return mId;
	}

	public FileboxEntryModel getParent() {
		return mParent;
	}

	public void setParent(FileboxEntryModel parent) {
		mParent = parent;
	}

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

	public boolean isFolder() {
		return mIsFolder;
	}

	public void setIsFolder(boolean isFolder) {
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
