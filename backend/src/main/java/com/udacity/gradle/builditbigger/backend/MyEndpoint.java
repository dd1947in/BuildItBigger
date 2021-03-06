/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.udacity.gradle.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.udacity.gradle.jokes.Joke;
import com.udacity.gradle.jokes.JokesProvider;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        JokesProvider jokesProvider = new JokesProvider();
        int flavor = JokesProvider.JOKE_FLAVOR_FREE;

        if(name.equalsIgnoreCase("PAID_APP") || name.equalsIgnoreCase("2")) {
            flavor = JokesProvider.JOKE_FLAVOR_PAID;
        }
        Joke joke = jokesProvider.getARandomJoke(flavor);
        response.setData(joke.getJoke());
        return response;
    }

}
