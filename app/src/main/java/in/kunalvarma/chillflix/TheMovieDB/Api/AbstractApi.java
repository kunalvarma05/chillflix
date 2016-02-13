package in.kunalvarma.chillflix.TheMovieDB.Api;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import in.kunalvarma.chillflix.TheMovieDB.Client;

abstract public class AbstractApi {

    private Client client;

    public AbstractApi(Client client) {
        this.setClient(client);
    }

    /**
     * Set the Client Object
     *
     * @param client Client Object
     */
    public void setClient(Client client) {
        //Set the client
        this.client = client;
    }

    /**
     * Return the Client Object
     *
     * @return Client
     */
    public Client getClient() {
        //Return the client
        return this.client;
    }

    /**
     * Make a GET request to the specified url/endpoint
     * with the specified params and returns back to
     * the specified response handler.
     *
     * @param endpoint        String
     * @param params          RequestParams
     * @param responseHandler AsyncHttpResponseHandler
     */
    public void get(String endpoint, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        this.client.getHttpClient().get(endpoint, params, responseHandler);
    }

    /**
     * Make a POST request to the specified url/endpoint
     * with the specified params and returns back to
     * the specified response handler.
     *
     * @param endpoint        String
     * @param params          RequestParams
     * @param responseHandler AsyncHttpResponseHandler
     */
    public void post(String endpoint, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        this.client.getHttpClient().post(endpoint, params, responseHandler);
    }

}
