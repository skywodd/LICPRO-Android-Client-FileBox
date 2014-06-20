package fr.licpro.filebox.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import fr.licpro.filebox.R;
import fr.licpro.filebox.constants.FileboxRuntimeConstants;
import fr.licpro.filebox.service.SyncService;
import fr.licpro.filebox.service.sync.ConnectionSync;

public class LoginActivity extends Activity implements OnClickListener {

	private Button mConnexion;

	private EditText mLogin, mPwd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		// adding = getIntent().getExtras().getBoolean(EXTRA_PARCEL_ADD);

		mLogin = (EditText) findViewById(R.id.tf_login_username);
		mPwd = (EditText) findViewById(R.id.tf_login_passwd);

		mConnexion = (Button) findViewById(R.id.btn_login);
		mConnexion.setOnClickListener(this);
		registerReceiver(new TokenReceiver(), new IntentFilter(FileboxRuntimeConstants.BROADCAST_FILTER));
	}

	@Override
	public void onClick(View v) {
		
		Crouton.showText(this, getString(R.string.login_in_progress),
				Style.INFO);

		Intent connectionService = new Intent(this, SyncService.class);
		connectionService.putExtra(SyncService.SYNC_CLASS_INTENT,
				new ConnectionSync(mLogin.getText().toString(), mPwd.getText().toString()));
		startService(connectionService);
	}
	
	class TokenReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			if(intent.getAction().equals(ConnectionSync.TOKEN_SUCCESS)){
				Log.i(ACTIVITY_SERVICE, getString(R.string.login_success));
			} else {
				Log.w(ACTIVITY_SERVICE, getString(R.string.login_failed));
			}
		      
			
			
		}
		
	}

}
