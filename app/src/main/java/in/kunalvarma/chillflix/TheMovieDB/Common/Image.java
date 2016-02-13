package in.kunalvarma.chillflix.TheMovieDB.Common;


import java.util.HashMap;
import java.util.Map;

public abstract class Image {

    /**
     * Base URL
     */
    public static String BASE_URL = "https://image.tmdb.org/t/p/";

    /**
     * Default Sizes
     */
    public static Map<String, String> DEFAULT_SIZES = new HashMap<>();


    /**
     * Fetch the URL of the image
     *
     * @param filePath The relative path of the file
     * @param size     SIze of the image
     */
    public static String getUrl(String filePath, String size) {
        return BASE_URL + resolveSize(size) + filePath;
    }


    /**
     * Resolve the size of the Image
     *
     * @param size Size of the image
     * @return String
     */
    public static String resolveSize(String size) {
        setDefaultSizes();
        size = size.length() < 1 ? "" : size;
        String lSize = size.toLowerCase();

        if (!getDefaultSizes().containsKey(lSize))
            lSize = "medium";

        return getDefaultSizes().get(lSize);
    }

    /**
     * Set the Default Image Sizes
     */
    private static void setDefaultSizes() {
        if (getDefaultSizes().isEmpty()) {
            getDefaultSizes().put("small-thumb", "w92");
            getDefaultSizes().put("thumb", "w154");
            getDefaultSizes().put("big-thumb", "w185");
            getDefaultSizes().put("small", "w342");
            getDefaultSizes().put("medium", "w500");
            getDefaultSizes().put("large", "w780");
            getDefaultSizes().put("original", "original");
        }
    }

    /**
     * Get the Default Image Sizes
     *
     * @return Map
     */
    private static Map<String, String> getDefaultSizes() {
        return DEFAULT_SIZES;
    }

}
