package in.kunalvarma.chillflix.TheMovieDB.Api;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import in.kunalvarma.chillflix.TheMovieDB.Client;

public class Discover extends AbstractApi {

    /**
     * Constructor
     *
     * @param client The Movie Database Client
     */
    public Discover(Client client) {
        super(client);
    }

    /**
     * Discover Movies
     *
     * @param requestParams   Request Params to send along
     * @param responseHandler Response Handler to handle the response
     */
    public void getMovies(RequestParams requestParams, AsyncHttpResponseHandler responseHandler) {
        this.get("discover/movie", requestParams, responseHandler);
    }

    /**
     * Discover Movies
     *
     * @param responseHandler Response Handler to handle the response
     */
    public void getMovies(AsyncHttpResponseHandler responseHandler) {
        getMovies(new RequestParams(), responseHandler);
    }

}
