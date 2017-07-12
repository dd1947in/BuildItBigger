package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragmentFree extends Fragment {
    private String applicationId = "com.udacity.gradle.builditbigger.free" ;
    public MainActivityFragmentFree() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        TextView tvMessage = (TextView) root.findViewById(R.id.tv_message);
        Context context = getContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(com.udacity.gradle.jokeactivity.R.string.app_shared_preferences_file), Context.MODE_PRIVATE);
        applicationId = sharedPreferences.getString(getString(com.udacity.gradle.jokeactivity.R.string.application_id_key), getString(com.udacity.gradle.jokeactivity.R.string.application_id_free));

        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        if(applicationId.equals(getString(com.udacity.gradle.jokeactivity.R.string.application_id_paid))) {
            mAdView.setVisibility(View.INVISIBLE);
            tvMessage.setText(getString(R.string.message_app_paid));
        } else {
            tvMessage.setText(getString(R.string.message_app_free));
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .build();
            mAdView.loadAd(adRequest);
        }
        return root;
    }
}
