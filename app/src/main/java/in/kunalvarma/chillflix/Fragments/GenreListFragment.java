package in.kunalvarma.chillflix.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anton46.collectionitempicker.CollectionPicker;

import in.kunalvarma.chillflix.BuildConfig;
import in.kunalvarma.chillflix.R;
import in.kunalvarma.chillflix.ResponseHandlers.GenreListHandler;
import in.kunalvarma.chillflix.TheMovieDB.Api.Genre;
import in.kunalvarma.chillflix.TheMovieDB.ApiToken;
import in.kunalvarma.chillflix.TheMovieDB.Client;

/**
 * A simple {@link Fragment} subclass.
 */
public class GenreListFragment extends Fragment {

    public GenreListFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Fragment Root View
        final View rootView = inflater.inflate(R.layout.fragment_genre_list, container, false);

        //Collection Picker
        CollectionPicker picker = (CollectionPicker) rootView.findViewById(R.id.collection_item_picker);

        //Initialize a new TMDB Client
        String apiKey = BuildConfig.MOVIE_DB_API_KEY;
        ApiToken apiToken = new ApiToken(apiKey);
        Client tmdbClient = new Client(apiToken);

        //Get the Discover API through the TMDB Client
        Genre genreApi = tmdbClient.getGenreApi();

        //Initialize and Build the Genre List Handler
        GenreListHandler genreListHandler = new GenreListHandler();
        genreListHandler.setPicker(picker);

        //Execute the Get Genres API Method
        genreApi.getGenres(genreListHandler);

        // Inflate the layout for this fragment
        return rootView;

    }


}
