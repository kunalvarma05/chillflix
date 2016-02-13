package in.kunalvarma.chillflix.TheMovieDB.Common;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import in.kunalvarma.chillflix.TheMovieDB.Model.Movie;

public class MovieCollection extends Collection {

    @SerializedName("results")
    public List<Movie> movies;

    public MovieCollection() {
        movies = new ArrayList<>();
    }

    public List<Movie> getMovies() {
        return this.movies;
    }
}
