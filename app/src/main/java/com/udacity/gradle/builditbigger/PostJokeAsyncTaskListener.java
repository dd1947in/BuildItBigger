package com.udacity.gradle.builditbigger;

/**
 * Created by dd2568 on 7/13/2017.
 */

public interface PostJokeAsyncTaskListener<T> {
    void onPostJokeAsyncTask(T result);
}
