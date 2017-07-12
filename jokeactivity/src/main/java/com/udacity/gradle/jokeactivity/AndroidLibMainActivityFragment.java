package com.udacity.gradle.jokeactivity;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


// No adds in android library as per rev 1
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;

import  android.util.Log.*;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A placeholder fragment containing a simple view.
 */
public class AndroidLibMainActivityFragment extends Fragment {
     private String applicationId = "com.udacity.gradle.builditbigger.free" ; //getString(R.string.application_id_free);
    public AndroidLibMainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_android_lib_main_activity, container, false);
        //We get the applicationId from shared preferences set by the MainActivity.
        // Paid / Free is determined by BuildConfig of the main app not this jokeactivity android app.
        Context context = getContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_shared_preferences_file), Context.MODE_PRIVATE);
        applicationId = sharedPreferences.getString(getString(R.string.application_id_key), getString(R.string.application_id_free));

         // Adds removed from library activity as per rev 1 only joke will be displayed
        //AdView mAdView = (AdView) root.findViewById(R.id.adViewAL);  ////No adds in library
        TextView tvMessage = (TextView) root.findViewById(R.id.tv_message_al) ;
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        if(applicationId.equals(getString(R.string.application_id_paid))) {
            //mAdView.setVisibility(View.INVISIBLE);  //No adds in library
            tvMessage.setText(getString(R.string.message_app_paid));
        } else {
            tvMessage.setText(getString(R.string.message_app_free));
            /*    //No adds in library
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .build();
            mAdView.loadAd(adRequest);
*/
            //System.out.print("AndroidLibMainActivityFragment:onCreateView");
            //Toast.makeText(getContext(), "AndroidLibMainActivityFragment:onCreateView", Toast.LENGTH_SHORT).show();
        }
        return root;
    }
}
