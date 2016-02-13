package in.kunalvarma.chillflix.TheMovieDB;


public class ApiToken {

    /**
     * Api Token
     */
    protected String apiToken;

    /**
     * Constructor
     *
     * @param apiToken String
     */
    public ApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    /**
     * Return the Api Key
     *
     * @return apiToken String
     */
    public String getApiKey() {
        return apiToken;
    }

    /**
     * Set the Api Key
     *
     * @param apiToken String
     */
    public void setApiKey(String apiToken) {
        this.apiToken = apiToken;
    }
}
