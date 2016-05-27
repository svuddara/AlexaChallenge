package automation.home.visa.com.homeautomation.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import automation.home.visa.com.homeautomation.GcmIntentService;
import automation.home.visa.com.homeautomation.R;
import automation.home.visa.com.homeautomation.fragment.CheckoutMapFragment;
import automation.home.visa.com.homeautomation.fragment.ReceiptFragment;
import automation.home.visa.com.homeautomation.fragment.TicketReceiptFragment;
import automation.home.visa.com.homeautomation.fragment.TicketViewFragment;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ReceiptFragment.OnFragmentInteractionListener,
                                                          CheckoutMapFragment.OnFragmentInteractionListener,
                                                            TicketViewFragment.OnFragmentInteractionListener,
                                                            TicketReceiptFragment.OnFragmentInteractionListener{

    private static final String TAG = MainActivity.class.getName();
    private GoogleCloudMessaging gcm;
    private static  String regId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        ButterKnife.bind(this);

        if(GcmIntentService.fragmenttoLoad == "checkout") {
            ((AppCompatActivity) this).getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new CheckoutMapFragment(), TAG)
                    .commitAllowingStateLoss();
        }else{
            ((AppCompatActivity) this).getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new TicketViewFragment(), TAG)
                    .commitAllowingStateLoss();
        }

        register();
    }

    private void register() {
        if (checkPlayServices()) {
            gcm = GoogleCloudMessaging.getInstance(this);
            registerInBackground(this);
            try {
                regId = getRegistrationId(this);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (regId.isEmpty()) {
                registerInBackground(this);
            } else {
               // toastUser("Registration ID already exists: " + regId);
            }
        } else {
            Log.e(TAG, "No valid Google Play Services APK found.");
        }
    }

    private void registerInBackground(final Context context) {
        new AsyncTask() {
            @Override
            protected String doInBackground(Object[] params) {
                String msg;
                String regId;
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(context);
                    }
                    regId = gcm.register("70008811749");
                    msg = "Device registered, registration ID: " + regId;
                    Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();


                    //sendRegistrationId(regId);

                   // storeRegistrationId(context, regId);
                    Log.i(TAG, msg);
                } catch (Exception ex) {
                    msg = "Error :" + ex.getMessage();
                    Log.e(TAG, msg);
                }
                return msg;
            }
        }.execute(null, null, null);
    }

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                //GooglePlayServicesUtil.getErrorDialog(resultCode, this, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.e(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

    private String getRegistrationId(Context context) throws Exception {
//        final SharedPreferences prefs =
//                getSharedPreferences(MainActivity.class.getSimpleName(), Context.MODE_PRIVATE);
//        String registrationId = prefs.getString(PROPERTY_REG_ID, "");
//        if (registrationId.isEmpty()) {
//            return "";
//        }
//
//        return registrationId;
        return "";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
