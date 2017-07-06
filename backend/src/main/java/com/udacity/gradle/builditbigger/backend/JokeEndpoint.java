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
        name = "jokeApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)

public class JokeEndpoint {
    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "serveJoke")
    public Joke serveJoke(@Named("flavor") String flavor) {
        int intFlavor = Integer.parseInt(flavor);
        JokesProvider jokesProvider = new JokesProvider();

        Joke joke =jokesProvider.getARandomJoke(intFlavor);

        return joke;
    }

}
