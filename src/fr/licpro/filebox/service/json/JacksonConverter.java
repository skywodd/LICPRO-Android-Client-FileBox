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

package fr.licpro.filebox.service.json;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

/**
 * Converter class from JSON to Java objects. Allow the RetroFit library to
 * use the powerful Jackson library to turn raw JSON answer into Java objects.
 * 
 * @author julien, Skywodd
 */
public class JacksonConverter implements Converter {

	/**
	 * JSON encoding charset.
	 */
	private static final String JSON_CHARSET = "UTF-8";

	/**
	 * Object mapper for the java object manipulation.
	 */
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	/*
	 * (non-Javadoc)
	 * 
	 * @see retrofit.converter.Converter#fromBody(retrofit.mime.TypedInput,
	 * java.lang.reflect.Type)
	 */
	@Override
	public Object fromBody(TypedInput body, Type type)
			throws ConversionException {

		/* Construct a new java type using the type factory */
		JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructType(type);

		/* Catch runtime error */
		try {

			/* Read the value into the target type */
			return OBJECT_MAPPER.readValue(body.in(), javaType);

		} catch (IOException e) {

			/* Conversion error */
			throw new ConversionException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see retrofit.converter.Converter#toBody(java.lang.Object)
	 */
	@Override
	public TypedOutput toBody(Object object) {

		/* Catch runtime error */
		try {

			/* Write the value into the JSON file */
			return new JsonTypedOutput(OBJECT_MAPPER.writeValueAsString(object)
					.getBytes(JSON_CHARSET));

		} catch (IOException e) {

			/* Oops */
			throw new RuntimeException(e);
		}
	}

	/**
	 * Wrapping class between TypedOutput (for RetroFit) and byte[] (from
	 * Jackson);
	 * 
	 * @author julien, Skywodd
	 */
	private static class JsonTypedOutput implements TypedOutput {

		/**
		 * Mime type of the encoded JSON.
		 */
		private static final String JSON_MIMETYPE = "application/json; charset="
				+ JSON_CHARSET;

		/**
		 * Bytes array with all serialized JSON data.
		 */
		private final byte[] mJsonBytes;

		/**
		 * Constructor of the nested JsonTypedOutput class.
		 * 
		 * @param jsonBytes
		 *            Bytes array with all serialized JSON data.
		 */
		public JsonTypedOutput(byte[] jsonBytes) {
			mJsonBytes = jsonBytes;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see retrofit.mime.TypedOutput#fileName()
		 */
		@Override
		public String fileName() {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see retrofit.mime.TypedOutput#mimeType()
		 */
		@Override
		public String mimeType() {
			return JSON_MIMETYPE;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see retrofit.mime.TypedOutput#length()
		 */
		@Override
		public long length() {
			return mJsonBytes.length;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see retrofit.mime.TypedOutput#writeTo(java.io.OutputStream)
		 */
		@Override
		public void writeTo(OutputStream out) throws IOException {
			out.write(mJsonBytes);
		}

	}

}
