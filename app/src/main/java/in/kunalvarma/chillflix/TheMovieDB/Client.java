package in.kunalvarma.chillflix.TheMovieDB;

import com.loopj.android.http.AsyncHttpClient;

import java.util.HashMap;
import java.util.Map;

import in.kunalvarma.chillflix.TheMovieDB.Api.Discover;
import in.kunalvarma.chillflix.TheMovieDB.Api.Genre;

public class Client {

    private String API_TOKEN_KEY = "api_key";

    /**
     * API Token
     */
    private ApiToken apiToken;

    /**
     * Default Params
     */
    private Map<String, String> defaultParams = new HashMap<>();

    /**
     * The HTTPClient
     */
    private static HTTPClient httpClient;

    /**
     * The BASE URL of the API
     */
    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    /**
     * Client Constructor
     *
     * @param apiToken The Api Token
     */
    public Client(ApiToken apiToken) {
        //Set the API Key/Token
        this.setApiToken(apiToken);
        //Add the API Key as the default token for all requests
        this.addDefaultParam(API_TOKEN_KEY, this.apiToken.getApiKey());
        //Construct the HTTPClient
        this.constructHttpClient();
    }


    /**
     * Construct the HTTPClient
     */
    protected void constructHttpClient() {
        //Instantiate the HTTPClient
        httpClient = new HTTPClient(new AsyncHttpClient(), BASE_URL);
        //Set the Default Params for all requests
        httpClient.setDefaultParams(this.getDefaultParams());
    }

    /**
     * Get the HTTPClient
     *
     * @return HTTPClient
     */
    public HTTPClient getHttpClient() {
        //Return the HTTPClient
        return httpClient;
    }

    /**
     * Get Default Params
     *
     * @return Map
     */
    public Map<String, String> getDefaultParams() {
        return defaultParams;
    }

    /**
     * Set Default Params
     *
     * @param defaultParams Default Params
     */
    public void setDefaultParams(Map<String, String> defaultParams) {
        //Set the default params
        this.defaultParams = defaultParams;
    }

    /**
     * Add a key-value pair to the Default Param Map
     *
     * @param key   Param Key
     * @param value Param Value
     */
    public void addDefaultParam(String key, String value) {
        defaultParams = getDefaultParams();
        defaultParams.put(key, value);
    }

    /**
     * Set the API Token
     *
     * @return String
     */
    public ApiToken getApiToken() {
        //Return the APIToken
        return this.apiToken;
    }

    /**
     * Set the APIToken
     *
     * @param apiToken
     */
    public void setApiToken(ApiToken apiToken) {
        //Set the APIToken
        this.apiToken = apiToken;
    }


    /****************************************
     * The Movie Database APIs
     ***************************************/

    /**
     * Discover API Object
     *
     * @return Discover
     */
    public Discover getDiscoverApi() {
        return new Discover(this);
    }


    /**
     * Genre API Object
     *
     * @return Genre
     */
    public Genre getGenreApi() {
        return new Genre(this);
    }

}
