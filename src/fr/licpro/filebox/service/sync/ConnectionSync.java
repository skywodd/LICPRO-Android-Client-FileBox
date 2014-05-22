package fr.licpro.filebox.service.sync;

import retrofit.RetrofitError;
import fr.licpro.filebox.dto.FileboxAuthToken;
import fr.licpro.filebox.service.IRestClient;


/**
 * Method to Sync the startUp data
 */
public class ConnectionSync extends AbstractSync<FileboxAuthToken> {

	
	@Override
	protected FileboxAuthToken execute(final IRestClient pRestClient) throws RetrofitError {
		return null;
	}

	@Override
	protected void onSuccess() {
		
	}

	@Override
	protected void onError(Exception e) {
		
	}

}
