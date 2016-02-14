package in.kunalvarma.chillflix.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

import in.kunalvarma.chillflix.Adapters.MovieListAdapter;
import in.kunalvarma.chillflix.BuildConfig;
import in.kunalvarma.chillflix.R;
import in.kunalvarma.chillflix.ResponseHandlers.MovieListHandler;
import in.kunalvarma.chillflix.TheMovieDB.ApiToken;
import in.kunalvarma.chillflix.TheMovieDB.Client;
import in.kunalvarma.chillflix.TheMovieDB.Model.Movie;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends Fragment {


    public MovieListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Fragment Root View
        final View rootView = inflater.inflate(R.layout.fragment_movie_list, container, false);

        //Initialize the MovieListAdapter
        final MovieListAdapter movieListAdapter = new MovieListAdapter(
                //Context
                getActivity(),
                //File containing the layout
                R.layout.movie_grid_item_view,
                //Movie Data
                new ArrayList<Movie>()
        );


        //Find the Movie List GridView
        GridView movieListGridView = ((GridView) rootView.findViewById(R.id.movie_list_grid_view));
        //Set the adapter
        movieListGridView.setAdapter(movieListAdapter);


        //Initialize a new TMDB Client
        String apiKey = BuildConfig.MOVIE_DB_API_KEY;
        ApiToken apiToken = new ApiToken(apiKey);
        Client tmdbClient = new Client(apiToken);

        //Get the Discover API through the TMDB Client
        in.kunalvarma.chillflix.TheMovieDB.Api.Discover discoverApi = tmdbClient.getDiscoverApi();

        //Initialize and Build the Movie List Handler
        MovieListHandler movieListHandler = new MovieListHandler();
        movieListHandler.setAdapter(movieListAdapter);

        //Execute the Get Movies API Method
        discoverApi.getMovies(movieListHandler);


        // Inflate the layout for this fragment
        return rootView;
    }


}
