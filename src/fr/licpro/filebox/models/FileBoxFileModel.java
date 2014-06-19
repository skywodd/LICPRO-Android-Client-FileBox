package fr.licpro.filebox.models;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

import fr.licpro.filebox.constants.MimeTypeEnum;

public class FileBoxFileModel {
	
	/**
	 * The filename.
	 */
	private String mFilename;

	/**
	 * The file hash.
	 */
	private String mFileHash;

	/**
	 * True if the file is a folder.
	 */
	private Boolean mIsFolder;

	/**
	 * The file type.
	 */
	private MimeTypeEnum mFileType;

	/**
	 * The last modification date (UNIX time stamp).
	 */
	private Date mLastModification;
}
