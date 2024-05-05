package cs1302.api;

/**
 * Represents a response from the Joke API. This is used by Gson to create an object
 * from the JSON response body.
 */
public class JokeResponse {

    boolean error;
    String joke;

}
