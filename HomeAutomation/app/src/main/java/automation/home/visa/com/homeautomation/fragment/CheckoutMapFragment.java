package automation.home.visa.com.homeautomation.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.pass.Spass;
import com.samsung.android.sdk.pass.SpassFingerprint;

import automation.home.visa.com.homeautomation.R;
import automation.home.visa.com.homeautomation.utils.NavigationUtils;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CheckoutMapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CheckoutMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckoutMapFragment extends Fragment {

//    private SpassFingerprint mSpassFingerprint;
//    private Spass mSpass;
    private OnFragmentInteractionListener mListener;
    private boolean isFeatureEnabled;
    private static final String TAG = CheckoutMapFragment.class.getName();

    @Bind(R.id.image)
    ImageView checkout;

    public static CheckoutMapFragment newInstance(String param1, String param2) {
        CheckoutMapFragment fragment = new CheckoutMapFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public CheckoutMapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initSpass();
    }

//    private SpassFingerprint.IdentifyListener listener = new SpassFingerprint.IdentifyListener() {
//        @Override
//        public void onFinished(int eventStatus) {
//            // It is called when fingerprint identification is finished.
//            if (eventStatus == SpassFingerprint.STATUS_AUTHENTIFICATION_SUCCESS) {
//                // Identify operation succeeded with fingerprint
//                proceedToHomePage();
//            } else if (eventStatus == SpassFingerprint.STATUS_AUTHENTIFICATION_PASSWORD_SUCCESS) {
//                // Identify operation succeeded with alternative password
//                proceedToHomePage();
//            } else {
//                Toast.makeText(getActivity(), "Authentication failed", Toast.LENGTH_SHORT).show();
//                //loading_back.setVisibility(View.GONE);
//                //progressBar.setVisibility(View.GONE);
//            }
//        }
//
//        @Override
//        public void onReady() {
//            // It is called when fingerprint identification is ready after
//            // startIdentify() is called.
//        }
//
//        @Override
//        public void onStarted() {
//            // It is called when the user touches the fingerprint sensor after
//            // startIdentify() is called.
//        }
//    };

    private void proceedToHomePage(){
        NavigationUtils.navigateToFragment(getActivity(), new ReceiptFragment(), R.id.fragment_container, TAG, true);
    }


//    private void initSpass() {
//        mSpass = new Spass();
//
//        try {
//            mSpass.initialize(getActivity());
//        } catch (UnsupportedOperationException e) {
//            // Error handling
//        } catch (SsdkUnsupportedException e) {
//            e.printStackTrace();
//        }
//    }

    private void fingerPrintDialog() {
        proceedToHomePage();
        return;

//        isFeatureEnabled = mSpass.isFeatureEnabled(Spass.DEVICE_FINGERPRINT);
//        if (isFeatureEnabled) {
//            mSpassFingerprint = new SpassFingerprint(getActivity());
//            if (mSpassFingerprint.hasRegisteredFinger()) {
//                mSpassFingerprint.startIdentifyWithDialog(getActivity(), listener, true);
//            } else {
//                Toast.makeText(getActivity(), "No registered fingerprint in the device.", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            Log.d(TAG, "Fingerprint Service is not supported in the device.");
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_checkout_map, container, false);
        ButterKnife.bind(this,view);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fingerPrintDialog();
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
