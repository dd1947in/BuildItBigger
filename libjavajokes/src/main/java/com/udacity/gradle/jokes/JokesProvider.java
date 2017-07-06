package com.udacity.gradle.jokes;

public class JokesProvider {
    public static final int JOKE_FLAVOR_FREE = 1;
    public static final int JOKE_FLAVOR_PAID = 2;

    private static final String paidJokesCollection[] = {
            // collection of good jokes . Do these really need to be jokes?
            "Premium Quality Paid App Joke 00",
            "Premium Quality Paid App Joke 01",
            "Premium Quality Paid App Joke 02",
            "Premium Quality Paid App Joke 03",
            "Premium Quality Paid App Joke 04",
            "Premium Quality Paid App Joke 05",
            "Premium Quality Paid App Joke 06",
            "Premium Quality Paid App Joke 07",
            "Premium Quality Paid App Joke 08",
            "Premium Quality Paid App Joke 09",
            "Premium Quality Paid App Joke 10",
            "Premium Quality Paid App Joke 11",
            "Premium Quality Paid App Joke 12",
            "Premium Quality Paid App Joke 13",
            "Premium Quality Paid App Joke 14",
            "Premium Quality Paid App Joke 15",
            "Premium Quality Paid App Joke 16",
            "Premium Quality Paid App Joke 17",
            "Premium Quality Paid App Joke 18",
            "Premium Quality Paid App Joke 19",
            "Premium Quality Paid App Joke 20",
            "Premium Quality Paid App Joke 21",
            "Premium Quality Paid App Joke 22",
            "Premium Quality Paid App Joke 23",
            "Premium Quality Paid App Joke 24",
            "Premium Quality Paid App Joke 25",
            "Premium Quality Paid App Joke 26",
            "Premium Quality Paid App Joke 27",
            "Premium Quality Paid App Joke 28",
            "Premium Quality Paid App Joke 29",
            "Premium Quality Paid App Joke 30",
            "Premium Quality Paid App Joke 31",
            "Premium Quality Paid App Joke 32",
            "Premium Quality Paid App Joke 33",
            "Premium Quality Paid App Joke 34",
            "Premium Quality Paid App Joke 35",
            "Premium Quality Paid App Joke 36",
            "Premium Quality Paid App Joke 37",
            "Premium Quality Paid App Joke 38",
            "Premium Quality Paid App Joke 39",
            "Premium Quality Paid App Joke 40",
            "Premium Quality Paid App Joke 41",
            "Premium Quality Paid App Joke 42",
            "Premium Quality Paid App Joke 43",
            "Premium Quality Paid App Joke 44",
            "Premium Quality Paid App Joke 45",
            "Premium Quality Paid App Joke 46",
            "Premium Quality Paid App Joke 47",
            "Premium Quality Paid App Joke 48",
            "Premium Quality Paid App Joke 49"
    };

    private static final  String freeJokesCollection[] = {
            // collection of bad/boring jokes . Do these really need to be jokes?
            "Average Quality Free App  Joke 00",
            "Average Quality Free App  Joke 01",
            "Average Quality Free App  Joke 02",
            "Average Quality Free App  Joke 03",
            "Average Quality Free App  Joke 04",
            "Average Quality Free App  Joke 05",
            "Average Quality Free App  Joke 06",
            "Average Quality Free App  Joke 07",
            "Average Quality Free App  Joke 08",
            "Average Quality Free App  Joke 09",
            "Average Quality Free App  Joke 10",
            "Average Quality Free App  Joke 11",
            "Average Quality Free App  Joke 12",
            "Average Quality Free App  Joke 13",
            "Average Quality Free App  Joke 14",
            "Average Quality Free App  Joke 15",
            "Average Quality Free App  Joke 16",
            "Average Quality Free App  Joke 17",
            "Average Quality Free App  Joke 18",
            "Average Quality Free App  Joke 19",
            "Average Quality Free App  Joke 20",
            "Average Quality Free App  Joke 21",
            "Average Quality Free App  Joke 22",
            "Average Quality Free App  Joke 23",
            "Average Quality Free App  Joke 24",
            "Average Quality Free App  Joke 25",
            "Average Quality Free App  Joke 26",
            "Average Quality Free App  Joke 27",
            "Average Quality Free App  Joke 28",
            "Average Quality Free App  Joke 29",
            "Average Quality Free App  Joke 30",
            "Average Quality Free App  Joke 31",
            "Average Quality Free App  Joke 32",
            "Average Quality Free App  Joke 33",
            "Average Quality Free App  Joke 34",
            "Average Quality Free App  Joke 35",
            "Average Quality Free App  Joke 36",
            "Average Quality Free App  Joke 37",
            "Average Quality Free App  Joke 38",
            "Average Quality Free App  Joke 39",
            "Average Quality Free App  Joke 40",
            "Average Quality Free App  Joke 41",
            "Average Quality Free App  Joke 42",
            "Average Quality Free App  Joke 43",
            "Average Quality Free App  Joke 44",
            "Average Quality Free App  Joke 45",
            "Average Quality Free App  Joke 46",
            "Average Quality Free App  Joke 47",
            "Average Quality Free App  Joke 48",
            "Average Quality Free App  Joke 49"
    };

    public Joke getARandomJoke(int flavor) {
        int jokeId = (int) (Math.random() * 50);
        Joke joke = new Joke();
        if(flavor == JOKE_FLAVOR_PAID) {
            joke.setJoke(paidJokesCollection[jokeId]);
        } else {
            joke.setJoke(freeJokesCollection[jokeId]);
        }
        return joke;
    }

}
