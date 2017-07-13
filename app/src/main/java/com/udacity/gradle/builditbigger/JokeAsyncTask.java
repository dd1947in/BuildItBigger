package com.udacity.gradle.builditbigger;

/**
 * Created by dd2568 on 7/12/2017.
 */

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.jokeApi.JokeApi;
import java.io.IOException;
public class JokeAsyncTask  extends AsyncTask <String, Void, String> {
    private  JokeApi myApiService = null;
    private PostJokeAsyncTaskListener<String> postJokeAsyncTaskListener;
     JokeAsyncTask(PostJokeAsyncTaskListener<String> postJokeAsyncTaskListener) {
            this.postJokeAsyncTaskListener = postJokeAsyncTaskListener;
     }
    @Override
    protected String doInBackground(String... params) {
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

        String flavor = params[0];
        try {

            Log.d("doInBackground", "myApiService.serveJoke(name).execute().getJoke();");
            String joke = myApiService.serveJoke(flavor).execute().getJoke();
            return joke;
        } catch (IOException e) {
            return e.getMessage();
        }

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(postJokeAsyncTaskListener != null) {
            Log.d("onPostExecute", s);
            postJokeAsyncTaskListener.onPostJokeAsyncTask(s);  // Return joke
        }

    }
}
