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

import java.io.Serializable;
import java.util.Date;

import com.j256.ormlite.field.DataType;
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
public class FileboxEntryModel implements Serializable,
		Comparable<FileboxEntryModel> {

	/**
	 * Serialiation UID.
	 */
	private static final long serialVersionUID = 9002103950618184199L;

	/**
	 * The file hash. Also used as database ID.
	 */
	@DatabaseField(id = true, columnName = "hashId")
	private String mFileHash;

	/**
	 * The parent Filebox entry ID, or null if no parent (root directory)
	 */
	@DatabaseField(foreign = true, canBeNull = true, columnName = "parentHashId")
	private FileboxEntryModel mParent;

	/**
	 * The filename.
	 */
	@DatabaseField(columnName = "name")
	private String mFilename;

	/**
	 * True if the file is a folder.
	 */
	@DatabaseField(columnName = "isFolder")
	private boolean mIsFolder;

	// File mimetype is determinate at runtime to allow more file types support
	// in future update.

	/**
	 * The last modification date (UNIX time stamp).
	 */
	@DatabaseField(columnName = "lastModification", dataType = DataType.DATE)
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
	 * @param lastModification
	 *            The last modification date (UNIX time stamp).
	 */
	public FileboxEntryModel(String fileHash, FileboxEntryModel parent,
			String filename, Boolean isFolder, Date lastModification) {
		mParent = parent;
		mFilename = filename;
		mFileHash = fileHash;
		mIsFolder = isFolder;
		mLastModification = lastModification;
	}

	/**
	 * Get the file hash ID.
	 * 
	 * @return The file hash ID.
	 */
	public String getFileHash() {
		return mFileHash;
	}

	/**
	 * Get the parent folder.
	 * 
	 * @return The parent folder, or null if no parent (root directory).
	 */
	public FileboxEntryModel getParent() {
		return mParent;
	}

	/**
	 * Get the filename.
	 * 
	 * @return The filename.
	 */
	public String getFilename() {
		return mFilename;
	}

	/**
	 * Return true if the file is a folder.
	 * 
	 * @return True if the file is a folder, false otherwise.
	 */
	public boolean isFolder() {
		return mIsFolder;
	}

	/**
	 * Get the file mimetype.
	 * 
	 * @return The file mimetype, or null if the file type is unknown.
	 */
	public MimeTypeEnum getFileType() {
		return MimeTypeEnum.getType(mFilename);
	}

	/**
	 * Get the last modification date.
	 * 
	 * @return The last modification date.
	 */
	public Date getLastModificationDate() {
		return mLastModification;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(FileboxEntryModel another) {

		/* The file name is used to sort files in alpha order */
		return mFilename.compareTo(another.mFilename);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		/* It's me, mario */
		if (obj == this) {
			return true;
		}

		/* Check against null and type */
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}

		/* Ok, let's cast */
		FileboxEntryModel fem = (FileboxEntryModel) obj;

		/* Two files are equals if the hashdId is the same */
		return mFileHash.equals(fem.mFileHash);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		/* The file hash is used as hash code */
		return mFileHash.hashCode();
	}

}
