package in.kunalvarma.chillflix.Listeners;

import android.view.View;
import android.widget.GridView;

import com.anton46.collectionitempicker.CollectionPicker;
import com.anton46.collectionitempicker.Item;
import com.loopj.android.http.RequestParams;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import in.kunalvarma.chillflix.Adapters.MovieListAdapter;
import in.kunalvarma.chillflix.Helper;
import in.kunalvarma.chillflix.R;
import in.kunalvarma.chillflix.Services.MovieService;


public class SlideUpPanelListener implements SlidingUpPanelLayout.PanelSlideListener {
    @Override
    public void onPanelSlide(View panel, float slideOffset) {

    }

    @Override
    public void onPanelExpanded(View panel) {

    }

    @Override
    public void onPanelCollapsed(View panel) {
        //Find the Collection Picker inside the panel view
        CollectionPicker picker = (CollectionPicker) panel.findViewById(R.id.collection_item_picker);

        //Get the items checked by the user
        Map<String, Object> items = picker.getCheckedItems();
        //Create an object with their values
        Collection<Object> selectedGenres = items.values();

        //Iterator object
        Iterator iterator = selectedGenres.iterator();


        //Create an array with the keys
        List<String> keys = new ArrayList<String>();

        while (iterator.hasNext()) {
            Item genre = (Item) iterator.next();
            keys.add(genre.id);
        }

        //Create a comma separated string from
        //keys array for the API Request Param
        String genreIDs = Helper.implode(",", keys);

        //Find the MovieListGridView
        GridView movieListGridView = ((GridView) panel.getRootView().findViewById(R.id.movie_list_grid_view));
        //Get the Adapter attached to the gridview
        MovieListAdapter movieListAdapter = ((MovieListAdapter) movieListGridView.getAdapter());

        //Set params for the API Request
        RequestParams params = new RequestParams();
        params.add("with_genres", genreIDs);
        params.add("sort_by", "popularity.desc");

        //Init the MovieService and class updateMovies
        MovieService movieService = new MovieService();
        movieService.updateMovies(movieListAdapter, params);

    }

    @Override
    public void onPanelAnchored(View panel) {

    }

    @Override
    public void onPanelHidden(View panel) {

    }
}
