package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.jokeApi.JokeApi;
import com.udacity.gradle.jokeactivity.AndroidLibMainActivity;

import java.io.IOException;
//import com.udacity.gradle.jokes.JokesProvider;
/*
package com.udacity.gradle.builditbigger;

public final class BuildConfig {
  public static final boolean DEBUG = false;
  public static final String APPLICATION_ID = "com.udacity.gradle.builditbigger.free";
  public static final String BUILD_TYPE = "release";
  public static final String FLAVOR = "free";
  public static final int VERSION_CODE = 1;
  public static final String VERSION_NAME = "1.0";
}
 */

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

    public void tellJoke(View view) {
        //Toast.makeText(this, "derp", Toast.LENGTH_SHORT).show();
        //new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "Manfred"));


        new GoogleCloudEndpointsJokeAsyncTask().execute(new Pair<Context, String>(this, AppVariant));
        /*
        Toast.makeText(this, new JokesProvider().getARandomJoke(JokesProvider.JOKE_FLAVOR_PAID).toString(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, AndroidLibMainActivity.class);
        intent.putExtra("JOKE", new JokesProvider().getARandomJoke(JokesProvider.JOKE_FLAVOR_PAID).toString());
        startActivity(intent);
        */
    }
    public void startJokeActivity() {
        Intent intent = new Intent(mContext, AndroidLibMainActivity.class);
        intent.putExtra("JOKE", mJoke);
        intent.putExtra("APP_VARIANT", AppVariant);
        startActivity(intent);

    }

    public class GoogleCloudEndpointsJokeAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
        private  JokeApi myApiService = null;
        private  Context context;

        @Override
        protected String doInBackground(Pair<Context, String>... params) {
            if(myApiService == null) {  // Only do this once
                JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        // - turn off compression when running against local devappserver
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {

                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                // end options for devappserver

                myApiService = builder.build();
            }

            context = params[0].first;
            String flavor = params[0].second;


            try {

                Log.d("doInBackground", "myApiService.serveJoke(name).execute().getJoke();");
                String joke = myApiService.serveJoke(flavor).execute().getJoke();
                return joke;
            } catch (IOException e) {
                return e.getMessage();
            }

        }
        @Override
        protected void onPostExecute(String result) {
            Log.d("onPostExecute", result);
            mJoke = result;
            //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
            startJokeActivity();

        }

    }


}
