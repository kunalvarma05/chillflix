package in.kunalvarma.chillflix.TheMovieDB.Common;

public class Error {

    private int statusCode;

    private String statusMessage;

    /**
     * Constructor
     */
    public Error() {
        this.statusCode = 0;
        this.statusMessage = "Something went wrong.";
    }

    /**
     * Get the Status Code
     *
     * @return statusCode
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Get the Status Message
     *
     * @return statusMessage
     */
    public String getStatusMessage() {
        return statusMessage;
    }
}
