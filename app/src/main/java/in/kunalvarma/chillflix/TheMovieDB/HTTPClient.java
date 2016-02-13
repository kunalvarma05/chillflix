package in.kunalvarma.chillflix.TheMovieDB;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HTTPClient {

    /**
     * BASE_URL of the API Endpoint
     */
    private String BASE_URL;

    /**
     * AsyncHttpClient Object
     */
    private static AsyncHttpClient client;

    /**
     * Default Parameters
     */
    private Map<String, String> defaultParams;


    /**
     * Construct the HTTPClient
     *
     * @param asyncHttpClient AsyncHttpClient
     * @param baseURL         The Base URL
     */
    public HTTPClient(AsyncHttpClient asyncHttpClient, String baseURL) {
        client = asyncHttpClient;
        this.BASE_URL = baseURL;
    }

    /**
     * Set Default Params
     *
     * @param defaultParams Default Params
     */
    public void setDefaultParams(Map<String, String> defaultParams) {
        this.defaultParams = defaultParams;
    }

    /**
     * Get Default Params
     *
     * @return Map
     */
    public Map<String, String> getDefaultParams() {
        return this.defaultParams;
    }

    /**
     * Build Request Params, merge default params into provided params
     *
     * @param requestParams RequestParams
     * @return RequestParams
     */
    public RequestParams buildParams(RequestParams requestParams) {
        Map<String, String> defaultParams = this.defaultParams;
        Set set = defaultParams.entrySet();
        Iterator i = set.iterator();

        while (i.hasNext()) {
            Map.Entry param = (Map.Entry) i.next();
            requestParams.add(param.getKey().toString(), param.getValue().toString());
        }

        return requestParams;
    }


    /**
     * Make a GET request to the specified url/endpoint
     * with the specified params and returns back to
     * the specified response handler.
     *
     * @param url             String
     * @param params          RequestParams
     * @param responseHandler AsyncHttpResponseHandler
     */
    public void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        RequestParams requestParams = buildParams(params);
        client.get(getAbsoluteUrl(url), requestParams, responseHandler);
    }

    /**
     * Make a POST request to the specified url/endpoint
     * with the specified params and returns back to
     * the specified response handler.
     *
     * @param url             String
     * @param params          RequestParams
     * @param responseHandler AsyncHttpResponseHandler
     */
    public void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        RequestParams requestParams = buildParams(params);
        client.post(getAbsoluteUrl(url), requestParams, responseHandler);
    }

    /**
     * Get the Absolute URL to the API endpoint
     *
     * @param relativeUrl String
     * @return String
     */
    public String getAbsoluteUrl(String relativeUrl) {
        return this.BASE_URL + relativeUrl;
    }

}
