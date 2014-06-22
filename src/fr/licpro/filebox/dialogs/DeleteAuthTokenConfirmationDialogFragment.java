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

package fr.licpro.filebox.dialogs;

import fr.licpro.filebox.R;
import fr.licpro.filebox.constants.FileboxRuntimeConstants;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

/**
 * Confirmation dialog displayed when deleting the authentication token.
 * 
 * @author Fabien Batteix
 */
public class DeleteAuthTokenConfirmationDialogFragment extends DialogFragment
		implements DialogInterface.OnClickListener, FileboxRuntimeConstants {

	/**
	 * Dialog fragment name.
	 */
	public static final String FRAGMENT_NAME = "DeleteAuthTokenConfirmationDialogFragment"; //$NON-NLS-1$

	/**
	 * Callback listener interface for the confirmation dialog.
	 * 
	 * @author Fabien Batteix
	 */
	public interface OnDeleteAuthTokenConfirmationListener {

		/**
		 * Called when the user confirm his wish to delete the authentication
		 * token.
		 * 
		 * @param dialog
		 *            The source dialog instance.
		 */
		void onDeleteAuthTokenConfirmed(
				DeleteAuthTokenConfirmationDialogFragment dialog);

	}

	/**
	 * Callback for events handling.
	 */
	private OnDeleteAuthTokenConfirmationListener mCallback;

	/**
	 * Show the dialog using the FRAGMENT_NAME as tag.
	 * 
	 * @param fm
	 *            The support fragment manager to use.
	 */
	public static void showDialog(FragmentManager fm) {

		/* Look for a previous dialog instance */
		DeleteAuthTokenConfirmationDialogFragment dialog = (DeleteAuthTokenConfirmationDialogFragment) fm
				.findFragmentByTag(FRAGMENT_NAME);

		/* If no previous instance */
		if (dialog == null) {

			/* Show the dialog */
			dialog = new DeleteAuthTokenConfirmationDialogFragment();
			dialog.show(fm, FRAGMENT_NAME);

			/* Avoid multiple dialog creation */
			fm.executePendingTransactions();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.DialogFragment#onAttach(android.app.Activity)
	 */
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.i(LOGCAT_TAG,
				"DeleteAuthTokenConfirmationDialogFragment::onAttach()"); //$NON-NLS-1$

		/* Verify that the host activity implements the callback interface */
		try {

			/*
			 * Store the OnDeleteAuthTokenConfirmationListener instance so we
			 * can send events to the host.
			 */
			mCallback = (OnDeleteAuthTokenConfirmationListener) activity;

		} catch (ClassCastException e) {

			/* The activity doesn't implement the interface, throw exception */
			throw new ClassCastException(activity.getClass().getName()
					+ " must implement OnDeleteAuthTokenConfirmationListener"); //$NON-NLS-1$
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.DialogFragment#onCreateDialog(android.os.Bundle)
	 */
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Log.i(LOGCAT_TAG,
				"DeleteAuthTokenConfirmationDialogFragment::onCreateDialog()"); //$NON-NLS-1$

		/* Build the dialog */
		return createDialog(R.string.message_alert_confirm_delete_auth_token,
				R.string.title_alert_confirm_delete_auth_token);
	}

	/**
	 * Build the alert dialog with the given message and title.
	 * 
	 * @param messadeResId
	 *            The message string resource ID.
	 * @param titleResID
	 *            The title string resource ID.
	 * @return The alert dialog instance.
	 */
	protected Dialog createDialog(int messadeResId, int titleResID) {

		/* Build the AlertDialog */
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		/* Add the warning icon */
		builder.setIcon(R.drawable.ic_action_warning);

		/* Add a title and a message */
		builder.setTitle(titleResID);
		builder.setMessage(messadeResId);

		/* Add an OK button */
		builder.setPositiveButton(android.R.string.yes, this);

		/* Add a cancel button */
		builder.setNegativeButton(android.R.string.no, this);

		/* Build the dialog */
		return builder.create();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.DialogFragment#onDetach()
	 */
	@Override
	public void onDetach() {
		super.onDetach();
		Log.i(LOGCAT_TAG,
				"DeleteAuthTokenConfirmationDialogFragment::onDetach()"); //$NON-NLS-1$

		/* Avoid leaking the activity instance */
		mCallback = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.content.DialogInterface.OnClickListener#onClick(android.content
	 * .DialogInterface, int)
	 */
	@Override
	public void onClick(DialogInterface dialog, int which) {
		Log.i(LOGCAT_TAG,
				"DeleteAuthTokenConfirmationDialogFragment::onClick()"); //$NON-NLS-1$

		/* Do only if the positive button is clicked */
		if (which == DialogInterface.BUTTON_POSITIVE) {

			/* Call the listener */
			mCallback.onDeleteAuthTokenConfirmed(this);
		}
	}

}