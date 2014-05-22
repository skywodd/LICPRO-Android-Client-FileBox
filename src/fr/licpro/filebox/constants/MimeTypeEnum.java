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

package fr.licpro.filebox.constants;

import org.apache.commons.lang3.StringUtils;

/**
 * Enumeration of all known file types and associated mime types. Each entry are
 * composed of a mime type and a file extension (DOS format).
 * 
 * @author julien, Skywodd
 */
public enum MimeTypeEnum {

	/**
	 * Adobe PDF file.
	 */
	PDF("application/pdf", "pdf"),

	/**
	 * Zip archive file.
	 */
	ZIP("application/zip", "zip"),

	/**
	 * MP3 audio file.
	 */
	MP3("audio/mpeg", "mp3"),

	/**
	 * Microsoft Wave audio file.
	 */
	WAVE("audio/wav", "wav"),

	/**
	 * Gif picture file.
	 */
	GIF("image/gif", "gif"),

	/**
	 * Jpeg picture file.
	 */
	JPEG("image/jpeg", "jpg"),

	/**
	 * Portable Network Graphics picture file.
	 */
	PNG("image/png", "png"),

	/**
	 * Comma separated values file.
	 */
	CSV("text/csv", "csv"),

	/**
	 * HTML file.
	 */
	HTML("text/html", "html"),

	/**
	 * Plain text file.
	 */
	TEXT("text/plain", "txt"),

	/**
	 * XML document file.
	 */
	XML("text/xml", "xml"),

	/**
	 * MP4 video file.
	 */
	MP4("video/mp4", "mp4"),

	/**
	 * Microsoft AVI video file.
	 */
	AVI("video/x-msvideo", "avi"),

	/**
	 * Vcard contact file.
	 */
	VCARD("text/vcard", "vcf");

	/**
	 * The file mime-type.
	 */
	private final String mMimeType;

	/**
	 * The file extension associated with the mime-type.
	 */
	private final String mExtension;

	/**
	 * Constructor of an element of MimeTypeEnum.
	 * 
	 * @param mimeType
	 *            The file mime-type.
	 * @param extension
	 *            The file extension associated with the mime-type.
	 */
	private MimeTypeEnum(final String mimeType, final String extension) {
		mMimeType = mimeType;
		mExtension = extension;
	}

	/**
	 * Get the file mime-type.
	 * 
	 * @return The file mime-type.
	 */
	public String getMimeType() {
		return mMimeType;
	}

	/**
	 * Get the MimeTypeEnum of the specified filename using the file extension.
	 * 
	 * @param filename
	 *            The input filename.
	 * @return The MimeTypeEnum of the given filename, or null if the file
	 *         extension is unknown.
	 */
	public static MimeTypeEnum getType(String filename) {

		/* Mime-type unknown by default */
		MimeTypeEnum result = null;

		/*
		 * If the filename is not blank (not null, not empty, not
		 * whitespace-only)
		 */
		if (StringUtils.isNotBlank(filename)) {

			/* For each known mime-type */
			for (MimeTypeEnum type : MimeTypeEnum.values()) {

				/* If the extensions matches */
				if (filename.endsWith(type.mExtension)) {

					/* Store the result */
					result = type;
				}
			}
		}

		/* Return the result */
		return result;
	}

}
