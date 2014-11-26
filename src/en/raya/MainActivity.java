package en.raya;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.plus.Plus;
import com.google.example.games.basegameutils.BaseGameUtils;

public class MainActivity extends Activity implements
		GoogleApiClient.ConnectionCallbacks,
		GoogleApiClient.OnConnectionFailedListener {
	final static String MY_ACHIEVEMEMENT_ID = "CgkIm-7a0KoWEAIQBQ";
	public static int jug = 0;
	private static GoogleApiClient mGoogleApiClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getActionBar().hide();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mGoogleApiClient = new GoogleApiClient.Builder(this)
				.addConnectionCallbacks(this)
				.addOnConnectionFailedListener(this).addApi(Plus.API)
				.addScope(Plus.SCOPE_PLUS_LOGIN).addApi(Games.API)
				.addScope(Games.SCOPE_GAMES)
				// add other APIs and scopes here as needed
				.build();
		
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.

		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			Button un_jug = (Button) rootView.findViewById(R.id.jugador1);
			Button dos_jug = (Button) rootView.findViewById(R.id.jugador2);
			un_jug.getBackground().setColorFilter(Color.parseColor("#747bd7"),
					PorterDuff.Mode.MULTIPLY);
			dos_jug.getBackground().setColorFilter(Color.parseColor("#747bd7"),
					PorterDuff.Mode.MULTIPLY);
			un_jug.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					jug = 1;
					if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
					    // Call a Play Games services API method, for example:
						Toast.makeText(getActivity(), "unlock", Toast.LENGTH_SHORT).show();
					    Games.Achievements.unlock(mGoogleApiClient,MY_ACHIEVEMEMENT_ID);
					    
					} else {
					    // Alternative implementation (or warn user that they must
					    // sign in to use this feature)
					}
					// TODO Auto-generated method stub
					getFragmentManager().beginTransaction()
							.add(R.id.container, new UnJugador()).commit();
				}
			});
			dos_jug.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					jug = 2;
					// TODO Auto-generated method stub
					getFragmentManager().beginTransaction()
							.add(R.id.container, new DosJugadores()).commit();
				}
			});
			return rootView;
		}

		
	}
	protected static void prueba() {
		// TODO Auto-generated method stub
		
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			FragmentManager fragmentManager = getFragmentManager();
			DialogoSalirApp seguirJugando = new DialogoSalirApp();
			seguirJugando.show(fragmentManager, "salir");
		}
		return true;
	}

	@Override
	protected void onStart() {
		super.onStart();
		mGoogleApiClient.connect();
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (mGoogleApiClient.isConnected()) {
			mGoogleApiClient.disconnect();
		}
	}
	private static int RC_SIGN_IN = 9001;

	private boolean mResolvingConnectionFailure = false;
	private boolean mAutoStartSignInFlow = true;
	private boolean mSignInClicked = false;
	@Override
	public void onConnectionFailed(ConnectionResult connectionResult) {
		// TODO Auto-generated method stub
		if (mResolvingConnectionFailure) {
	        // already resolving
	        return;
	    }

	    // if the sign-in button was clicked or if auto sign-in is enabled,
	    // launch the sign-in flow
	    if (mSignInClicked || mAutoStartSignInFlow) {
	        mAutoStartSignInFlow = false;
	        mSignInClicked = false;
	        mResolvingConnectionFailure = true;

	        // Attempt to resolve the connection failure using BaseGameUtils.
	        // The R.string.signin_other_error value should reference a generic
	        // error string in your strings.xml file, such as "There was
	        // an issue with sign-in, please try again later."
	        if (!BaseGameUtils.resolveConnectionFailure(this,
	                mGoogleApiClient, connectionResult,
	                RC_SIGN_IN, "Otro error")) {
	            mResolvingConnectionFailure = false;
	        }
	    }
	}

	@Override
	public void onConnected(Bundle arg0) {
		// TODO Auto-generated method stub
		startActivityForResult(Games.Achievements.getAchievementsIntent(
	            mGoogleApiClient), 1);
	}

	@Override
	public void onConnectionSuspended(int arg0) {
		// TODO Auto-generated method stub

	    mGoogleApiClient.connect();
	}
	protected void onActivityResult(int requestCode, int resultCode,
	        Intent intent) {
	    if (requestCode == RC_SIGN_IN) {
	        mSignInClicked = false;
	        mResolvingConnectionFailure = false;
	        if (resultCode == RESULT_OK) {
	            mGoogleApiClient.connect();
	        } else {
	            // Bring up an error dialog to alert the user that sign-in
	            // failed. The R.string.signin_failure should reference an error
	            // string in your strings.xml file that tells the user they
	            // could not be signed in, such as "Unable to sign in."
	            BaseGameUtils.showActivityResultError(this,
	                resultCode, R.string.signin_failure,
	                R.string.signin_other_error, resultCode);
	        }
	    }
	}
}
