package in.kunalvarma.chillflix.Services;

import com.loopj.android.http.RequestParams;

import in.kunalvarma.chillflix.Adapters.MovieListAdapter;
import in.kunalvarma.chillflix.BuildConfig;
import in.kunalvarma.chillflix.ResponseHandlers.MovieListHandler;
import in.kunalvarma.chillflix.TheMovieDB.ApiToken;
import in.kunalvarma.chillflix.TheMovieDB.Client;


public class MovieService {

    protected Client tmdbClient;

    public MovieService() {
        //Initialize a new TMDB Client
        String apiKey = BuildConfig.MOVIE_DB_API_KEY;
        ApiToken apiToken = new ApiToken(apiKey);
        this.tmdbClient = new Client(apiToken);
    }

    public void updateMovies(MovieListAdapter adapter, RequestParams params) {

        //Get the Discover API through the TMDB Client
        in.kunalvarma.chillflix.TheMovieDB.Api.Discover discoverApi = this.tmdbClient.getDiscoverApi();

        //Initialize and Build the Movie List Handler
        MovieListHandler movieListHandler = new MovieListHandler();
        movieListHandler.setAdapter(adapter);

        //Execute the Get Movies API Method
        discoverApi.getMovies(params, movieListHandler);
    }
}
