package in.kunalvarma.chillflix.Listeners;

import android.util.Log;
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
        CollectionPicker picker = (CollectionPicker) panel.findViewById(R.id.collection_item_picker);
        Map<String, Object> items = picker.getCheckedItems();

        Collection<Object> selectedGenreIds = items.values();

        Iterator iterator = selectedGenreIds.iterator();

        List<String> keys = new ArrayList<String>();

        while (iterator.hasNext()) {
            Item genre = (Item) iterator.next();
            keys.add(genre.id);
        }

        String genreIDs = Helper.implode(",", keys);


        GridView movieListGridView = ((GridView) panel.getRootView().findViewById(R.id.movie_list_grid_view));
        MovieListAdapter movieListAdapter = ((MovieListAdapter) movieListGridView.getAdapter());

        RequestParams params = new RequestParams();
        params.add("with_genres", genreIDs);
        params.add("sort_by", "popularity.desc");

        MovieService movieService = new MovieService();
        movieService.updateMovies(movieListAdapter, params);

        Log.v("Params", params.toString());

    }

    @Override
    public void onPanelAnchored(View panel) {

    }

    @Override
    public void onPanelHidden(View panel) {

    }
}
