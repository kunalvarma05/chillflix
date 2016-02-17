package in.kunalvarma.chillflix.ResponseHandlers;

import android.content.Context;
import android.widget.Toast;

import com.anton46.collectionitempicker.CollectionPicker;
import com.anton46.collectionitempicker.Item;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import in.kunalvarma.chillflix.TheMovieDB.Common.Error;
import in.kunalvarma.chillflix.TheMovieDB.Model.Genre;
import in.kunalvarma.chillflix.TheMovieDB.Model.GenreList;


public class GenreListHandler extends JsonHttpResponseHandler {

    /**
     * Movie List Handler Object
     */
    private CollectionPicker genreListPicker;

    /**
     * Set the Picker
     *
     * @param picker CollectionPicker
     */
    public void setPicker(CollectionPicker picker) {
        this.genreListPicker = picker;
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
            GenreList genreList = gson.fromJson(response.toString(), GenreList.class);
            ArrayList<Genre> genres = (ArrayList<Genre>) genreList.getGenres();

            List<Item> items = new ArrayList<>();

            Iterator<Genre> iterator = genres.iterator();

            while (iterator.hasNext()) {
                Genre genre = iterator.next();
                String key = Integer.toString(genre.id);
                items.add(new Item(key, genre.name.toUpperCase()));
            }

            this.genreListPicker.setItems(items);
            this.genreListPicker.drawItemView();

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
            Context context = this.genreListPicker.getContext();
            String errorMessage = error.getStatusMessage();
            Toast errorToast = Toast.makeText(context, errorMessage, Toast.LENGTH_LONG);
            errorToast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
