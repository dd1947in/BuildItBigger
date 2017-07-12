package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.udacity.gradle.jokeactivity.AndroidLibMainActivity;


import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private  final String APPLICATION_ID_PAID = "com.udacity.gradle.builditbigger.paid" ;
    private  final String APPLICATION_ID_FREE = "com.udacity.gradle.builditbigger.free" ;
    private String applicationId;


    private Context mContext;
    private String mJoke;
    private String AppVariant = "1";  //Free flavor


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        applicationId = BuildConfig.APPLICATION_ID ;
        if(applicationId.endsWith(".paid")) {
            AppVariant = "2"; //paid
        }
        writeToSharedPref(getString(R.string.application_id_key), applicationId);  // Store application id in shared preferences to be used in later activities
        setContentView(R.layout.activity_main);

    }

    private void writeToSharedPref(String key, String val) {

        SharedPreferences sharedPref = mContext.getSharedPreferences(mContext.getString(R.string.app_shared_preferences_file), Context.MODE_PRIVATE) ; //getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, val);
        editor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
            try {

               mJoke = new JokeAsyncTask().execute( AppVariant).get();
           }catch(ExecutionException ee) {
               mJoke = getString(R.string.joke_error_message);

           }catch (InterruptedException ie) {
               mJoke = getString(R.string.joke_error_message);

           }
        startJokeActivity();
    }
    public void startJokeActivity() {
        Intent intent = new Intent(mContext, AndroidLibMainActivity.class);
        intent.putExtra("JOKE", mJoke);
        intent.putExtra("APP_VARIANT", AppVariant);
        startActivity(intent);

    }
}
