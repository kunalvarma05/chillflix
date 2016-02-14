package in.kunalvarma.chillflix.ResponseHandlers;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import in.kunalvarma.chillflix.Adapters.MovieListAdapter;
import in.kunalvarma.chillflix.TheMovieDB.Common.Error;
import in.kunalvarma.chillflix.TheMovieDB.Model.Discover;
import in.kunalvarma.chillflix.TheMovieDB.Model.Movie;


public class MovieListHandler extends JsonHttpResponseHandler {

    /**
     * Movie List Handler Object
     */
    private MovieListAdapter movieListAdapter;

    /**
     * Set the Adapter
     *
     * @param adapter MovieListAdapter
     */
    public void setAdapter(MovieListAdapter adapter) {
        this.movieListAdapter = adapter;
    }

    /**
     * Response fetched successfully
     *
     * @param statusCode Status Code
     * @param headers    Headers
     * @param response   JSONObject Response Body
     */
    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        try {
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();
            Discover discover = gson.fromJson(response.toString(), Discover.class);
            ArrayList<Movie> movies = (ArrayList<Movie>) discover.getMovies();
            this.movieListAdapter.addAll(movies);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Response fetched unsuccessfully
     *
     * @param statusCode    Status Code
     * @param headers       Header
     * @param throwable     Throwable
     * @param errorResponse Error Response Body
     */
    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        try {
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();
            Error error = gson.fromJson(errorResponse.toString(), Error.class);
            Context context = this.movieListAdapter.getContext();
            String errorMessage = error.getStatusMessage();
            Toast errorToast = Toast.makeText(context, errorMessage, Toast.LENGTH_LONG);
            errorToast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
