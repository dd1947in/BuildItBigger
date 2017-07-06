package com.udacity.gradle.jokeactivity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class AndroidLibMainActivity extends AppCompatActivity {
    String joke = "Joke!";
    String appVariant = "1" ; // Free

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_lib_main);
        Intent intent = getIntent();
        if(intent.hasExtra("JOKE")) {
            joke = intent.getStringExtra("JOKE");
            //Toast.makeText(this, joke, Toast.LENGTH_LONG).show();
        }
        if(intent.hasExtra("APP_VARIANT")) {
            appVariant = intent.getStringExtra("APP_VARIANT");
            //Toast.makeText(this, joke, Toast.LENGTH_LONG).show();
        }

        TextView mTextView  = (TextView) findViewById(R.id.tv_joke_text_view_al);
        mTextView.setText(joke);



    }
}
