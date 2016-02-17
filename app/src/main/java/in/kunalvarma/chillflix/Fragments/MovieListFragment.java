package in.kunalvarma.chillflix.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

import in.kunalvarma.chillflix.Adapters.MovieListAdapter;
import in.kunalvarma.chillflix.BuildConfig;
import in.kunalvarma.chillflix.R;
import in.kunalvarma.chillflix.Services.MovieService;
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

        //Send additional params
        //along with the API Request
        RequestParams params = new RequestParams();
        params.add("sort_by", "popularity.desc");

        //Init the MovieService and call updateMovies
        MovieService movieService = new MovieService();
        movieService.updateMovies(movieListAdapter, params);

        // Inflate the layout for this fragment
        return rootView;
    }


}
