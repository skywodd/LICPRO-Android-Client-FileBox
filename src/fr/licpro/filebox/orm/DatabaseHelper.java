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

package fr.licpro.filebox.orm;

import java.sql.SQLException;
import java.util.Date;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import fr.licpro.filebox.R;
import fr.licpro.filebox.constants.MimeTypeEnum;
import fr.licpro.filebox.models.FileboxEntryModel;

/**
 * Database helper class used to manage the creation and upgrading of the
 * database. This class also usually provides the DAOs used by the other
 * classes.
 * 
 * @authors Fabien Batteix
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	/** Name of the database for the application */
	private static final String DATABASE_NAME = "FileBoxClient.db";

	/** Database version number */
	private static final int DATABASE_VERSION = 1;

	/** Filebox entries DAO */
	private Dao<FileboxEntryModel, Integer> fileboxEntriesDao = null;

	/** Filebox entries DAO (runtime edition) */
	private RuntimeExceptionDao<FileboxEntryModel, Integer> fileboxEntriesRuntimeDao = null;

	/**
	 * Constructor of the DatabaseHelper class.
	 * 
	 * @param context
	 *            Parent context instance.
	 */
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION,
				R.raw.ormlite_config);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper#onCreate(android
	 * .database.sqlite.SQLiteDatabase,
	 * com.j256.ormlite.support.ConnectionSource)
	 */
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, FileboxEntryModel.class);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		RuntimeExceptionDao<FileboxEntryModel,Integer> dao = getFileboxEntryModelRuntimeDao();
		FileboxEntryModel entry = new FileboxEntryModel(null, "test.txt", "", false, MimeTypeEnum.TEXT, new Date());
		dao.create(entry);
		FileboxEntryModel entry2 = new FileboxEntryModel(null, "toto", "", true, null, new Date());
		dao.create(entry2);
		FileboxEntryModel entry3 = new FileboxEntryModel(entry2, "test2.txt", "", false, MimeTypeEnum.TEXT, new Date());
		dao.create(entry3);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper#onUpgrade(android
	 * .database.sqlite.SQLiteDatabase,
	 * com.j256.ormlite.support.ConnectionSource, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int oldVersion, int newVersion) {
		try {
			TableUtils.dropTable(connectionSource, FileboxEntryModel.class,
					true);
			// after we drop the old databases, we create the new ones
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper#close()
	 */
	@Override
	public void close() {
		super.close();
		fileboxEntriesDao = null;
		fileboxEntriesRuntimeDao = null;
	}

	/**
	 * Returns the Database Access Object (DAO) for our FileboxEntryModel class.
	 * It will create it or just give the cached value.
	 */
	public Dao<FileboxEntryModel, Integer> getFileboxEntryModelDao()
			throws SQLException {
		if (fileboxEntriesDao == null) {
			fileboxEntriesDao = getDao(FileboxEntryModel.class);
		}
		return fileboxEntriesDao;
	}

	/**
	 * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao
	 * for our FileboxEntryModel class. It will create it or just give the
	 * cached value. RuntimeExceptionDao only through RuntimeExceptions.
	 */
	public RuntimeExceptionDao<FileboxEntryModel, Integer> getFileboxEntryModelRuntimeDao() {
		if (fileboxEntriesRuntimeDao == null) {
			fileboxEntriesRuntimeDao = getRuntimeExceptionDao(FileboxEntryModel.class);
		}
		return fileboxEntriesRuntimeDao;
	}

}