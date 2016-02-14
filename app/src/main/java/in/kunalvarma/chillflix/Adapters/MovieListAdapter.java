package in.kunalvarma.chillflix.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import in.kunalvarma.chillflix.R;
import in.kunalvarma.chillflix.TheMovieDB.Common.Image;
import in.kunalvarma.chillflix.TheMovieDB.Model.Movie;


public class MovieListAdapter extends ArrayAdapter {

    private Context context;
    private int mResource;
    private LayoutInflater layoutInflater;

    // View lookup cache
    private static class ViewHolder {
        ImageView imageView;
    }


    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing the ImageView to use when
     *                 instantiating the adapter items.
     * @param objects  The objects to represent in the GridView.
     */
    public MovieListAdapter(Context context, int resource, List<Movie> objects) {
        super(context, resource, objects);
        this.context = context;
        this.mResource = resource;
        this.layoutInflater = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder; // view lookup cache stored in tag

        //Size the View isn't yet cached
        if (convertView == null) {
            //Create a View Holder
            viewHolder = new ViewHolder();
            //Inflate the view
            convertView = this.layoutInflater.inflate(this.mResource, parent, false);
            //Set the imageView property of the View Holder
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.movie_grid_image_view);
            //Set the view tag
            convertView.setTag(viewHolder);
        } else {
            //Use the View Holder to resolve the View
            viewHolder = (ViewHolder) convertView.getTag();
        }


        //Get the movie being inserted
        Movie movie = (Movie) getItem(position);
        //Get poster image url
        String url = Image.getUrl(movie.getPosterPath(), "big-thumb");
        //Load the image
        Picasso.with(this.context).load(url).into(viewHolder.imageView);

        //Return the view to for rendering in the GridView
        return convertView;
    }
}
