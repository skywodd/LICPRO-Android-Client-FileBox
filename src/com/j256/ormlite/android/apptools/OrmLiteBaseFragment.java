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

package com.j256.ormlite.android.apptools;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;

/**
 * Base class to use for fragments in Android.
 * 
 * You can simply call {@link #getHelper()} to get your helper class, or
 * {@link #getConnectionSource()} to get a {@link ConnectionSource}.
 * 
 * The method {@link #getHelper()} assumes you are using the default helper
 * factory -- see {@link OpenHelperManager}. If not, you'll need to provide your
 * own helper instances which will need to implement a reference counting
 * scheme. This method will only be called if you use the database, and only
 * called once for this fragment's life-cycle. 'close' will also be called once
 * for each call to createInstance.
 * 
 * @author skywodd
 */
public abstract class OrmLiteBaseFragment<H extends OrmLiteSqliteOpenHelper>
		extends Fragment {

	private volatile H helper;
	private volatile boolean created = false;
	private volatile boolean destroyed = false;
	private static Logger logger = LoggerFactory
			.getLogger(OrmLiteBaseFragment.class);

	/**
	 * Get a helper for this action.
	 */
	public H getHelper() {
		if (helper == null) {
			if (!created) {
				throw new IllegalStateException(
						"A call has not been made to onCreate() yet so the helper is null");
			} else if (destroyed) {
				throw new IllegalStateException(
						"A call to onDestroy has already been made and the helper cannot be used after that point");
			} else {
				throw new IllegalStateException(
						"Helper is null for some unknown reason");
			}
		} else {
			return helper;
		}
	}

	/**
	 * Get a connection source for this action.
	 */
	public ConnectionSource getConnectionSource() {
		return getHelper().getConnectionSource();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Fragment#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		if (helper == null) {
			helper = getHelperInternal(getActivity());
			created = true;
		}
		super.onCreate(savedInstanceState);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Fragment#onDestroy()
	 */
	@Override
	public void onDestroy() {
		super.onDestroy();
		releaseHelper(helper);
		destroyed = true;
	}

	/**
	 * This is called internally by the class to populate the helper object
	 * instance. This should not be called directly by client code unless you
	 * know what you are doing. Use {@link #getHelper()} to get a helper
	 * instance. If you are managing your own helper creation, override this
	 * method to supply this fragment with a helper instance.
	 * 
	 * <p>
	 * <b> NOTE: </b> If you override this method, you most likely will need to
	 * override the {@link #releaseHelper(OrmLiteSqliteOpenHelper)} method as
	 * well.
	 * </p>
	 */
	protected H getHelperInternal(Context context) {
		@SuppressWarnings({ "unchecked", "deprecation" })
		H newHelper = (H) OpenHelperManager.getHelper(context);
		logger.trace("{}: got new helper {} from OpenHelperManager", this,
				newHelper);
		return newHelper;
	}

	/**
	 * Release the helper instance created in
	 * {@link #getHelperInternal(Context)}. You most likely will not need to
	 * call this directly since {@link #onDestroy()} does it for you.
	 * 
	 * <p>
	 * <b> NOTE: </b> If you override this method, you most likely will need to
	 * override the {@link #getHelperInternal(Context)} method as well.
	 * </p>
	 */
	protected void releaseHelper(H helper) {
		OpenHelperManager.releaseHelper();
		logger.trace("{}: helper {} was released, set to null", this, helper);
		this.helper = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Fragment#toString()
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName() + "@"
				+ Integer.toHexString(super.hashCode());
	}

}