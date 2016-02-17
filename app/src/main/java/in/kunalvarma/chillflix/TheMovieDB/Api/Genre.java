package in.kunalvarma.chillflix.TheMovieDB.Api;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import in.kunalvarma.chillflix.TheMovieDB.Client;

public class Genre extends AbstractApi {

    /**
     * Constructor
     *
     * @param client The Genre Database Client
     */
    public Genre(Client client) {
        super(client);
    }

    /**
     * Genre Genres
     *
     * @param requestParams   Request Params to send along
     * @param responseHandler Response Handler to handle the response
     */
    public void getGenres(RequestParams requestParams, AsyncHttpResponseHandler responseHandler) {
        this.get("genre/movie/list", requestParams, responseHandler);
    }

    /**
     * Genre Genres
     *
     * @param responseHandler Response Handler to handle the response
     */
    public void getGenres(AsyncHttpResponseHandler responseHandler) {
        getGenres(new RequestParams(), responseHandler);
    }

}
