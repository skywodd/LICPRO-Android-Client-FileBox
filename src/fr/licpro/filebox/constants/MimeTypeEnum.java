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

import fr.licpro.filebox.R;

/**
 * Enumeration of all known file types and associated mime types. Each entry are
 * composed of a mimetype, a file extension (DOS format) and a thumbnail image.
 * 
 * @author julien, Skywodd
 */
public enum MimeTypeEnum {
	
	/**
	 * Adobe PDF file.
	 */
	PDF("application/pdf", "pdf", R.drawable.ic_mimetype_pdf),

	/**
	 * Zip archive file.
	 */
	//ZIP("application/zip", "zip", R.drawable.ic_mimetype_zip), // FIXME

	/**
	 * MP3 audio file.
	 */
	//MP3("audio/mpeg", "mp3", R.drawable.ic_mimetype_mp3), // FIXME

	/**
	 * Microsoft Wave audio file.
	 */
	//WAVE("audio/wav", "wav", R.drawable.ic_mimetype_wav), // FIXME

	/**
	 * Gif picture file.
	 */
	//GIF("image/gif", "gif", R.drawable.ic_mimetype_gif), // FIXME

	/**
	 * Jpeg picture file.
	 */
	JPEG("image/jpeg", "jpg", R.drawable.ic_mimetype_jpg),

	/**
	 * Portable Network Graphics picture file.
	 */
	PNG("image/png", "png", R.drawable.ic_mimetype_png),

	/**
	 * Comma separated values file.
	 */
	//CSV("text/csv", "csv", R.drawable.ic_mimetype_csv), // FIXME

	/**
	 * HTML file.
	 */
	HTML("text/html", "html", R.drawable.ic_mimetype_html),

	/**
	 * Plain text file.
	 */
	//TEXT("text/plain", "txt", R.drawable.ic_mimetype_txt), // FIXME

	/**
	 * XML document file.
	 */
	//XML("text/xml", "xml", R.drawable.ic_mimetype_xml), // FIXME

	/**
	 * MP4 video file.
	 */
	//MP4("video/mp4", "mp4", R.drawable.ic_mimetype_mp4), // FIXME

	/**
	 * Microsoft AVI video file.
	 */
	//AVI("video/x-msvideo", "avi", R.drawable.ic_mimetype_avi), // FIXME

	/**
	 * Vcard contact file.
	 */
	VCARD("text/vcard", "vcf", R.drawable.ic_mimetype_vcard);

	/**
	 * The file mimetype.
	 */
	private final String mMimeType;

	/**
	 * The file extension associated with the mimetype.
	 */
	private final String mExtension;

	/**
	 * The thumbnail image resource ID associated with the mimetype.
	 */
	private final int mThumbnailResourceId;

	/**
	 * Constructor of an element of MimeTypeEnum.
	 * 
	 * @param mimeType
	 *            The file mimetype.
	 * @param extension
	 *            The file extension associated with the mimetype.
	 */
	private MimeTypeEnum(final String mimeType, final String extension,
			final int thumbnailResourceId) {
		mMimeType = mimeType;
		mExtension = extension;
		mThumbnailResourceId = thumbnailResourceId;
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

	/**
	 * Get the thumbnail resource ID associated with the mime-type.
	 * 
	 * @return The thumbnail resource ID associated with the mime-type.
	 */
	public int getThumbnailResourceId() {
		return mThumbnailResourceId;
	}

}
