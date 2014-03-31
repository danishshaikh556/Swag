package com.prolific.swag.app;

import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.Response;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Path;
//Note this is a Singleton Design
/**
 * Created by Danish556 on 3/30/14.
 */


//Note This Class follows a SingleTon Design Pattern
public class RetrofitAPICall{

private static final String API_URL =    "http://interview-danish.herokuapp.com";
//Making a SIngle Instance
private static RetrofitAPICall instance =new RetrofitAPICall();

//Make Constructor Private
private RetrofitAPICall(){}

 public  static  RetrofitAPICall getInstance(){return instance;}
//Interface for GETRequests
interface GETInterface {
    @GET("/{book_URL}/{book_no_URL}")
    JsonElement getData(
            @Path("book_URL")      String book_URL,
            @Path("book_no_URL") String book_no_URL
    );
}


//Interface for DeleteRequests
interface DeleteInterface{
    @DELETE("/{single_bookOr_all}/{book_num_url}")
    Response deleteBook(
            @Path("single_bookOr_all") String single_bookOr_all,
            @Path("book_num_url")      String book_num_url
    );
}





    //Use this method to get a single book Using Retrofit@GET API
    public String getFromServerSingleBook(String bookId) throws Exception {

        // Create a very simple REST adapter which points the Your Site API  endpoint.
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .build();

        // Create an instance of our GETInterface API interface.
        GETInterface toGet       = restAdapter.create(GETInterface.class);
        // Fetch and print a list of the contributors to this library.
        JsonElement contributors = toGet.getData("books",bookId);

        //Parse Received Data
        String a           = contributors.toString();

        JSONObject da      = null;
        da      = new JSONObject(a);
        JSONObject Abook   = new JSONObject(da.get("book").toString());
        String toReturn    = Abook.get("id").toString();

        return toReturn;
    }




    //Gets all books available at server using Retrofit @GET API
    public HashMap<String,BookObject> getFromServerAllBooks () throws Exception
    {
        //Add headers to spoof android as a browse
        //Note this step is only specific to the particular server-
        //http://interview-danish.herokuapp.com/books
        //As it doest accept mobile connections
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Accept", "text/html,application/xhtml+xml");
                request.addHeader("Accept-Language", "en-US");
                request.addHeader("User-Agent", "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0");
                request.addHeader("Accept-Encoding", "gzip, deflate");
                request.addHeader("Host","interview-danish.herokuapp.com");
                request.addHeader("DNT", "1");
                request.addHeader("Connection", "Keep-Alive");
            }
        };

        // Create a very simple REST adapter which points the Your Site API  endpoint.
        RestAdapter restAdapter2 = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .setRequestInterceptor(requestInterceptor)
                .build();

        // Create an instance of our GETInterface API interface.
        GETInterface toGet = restAdapter2.create(GETInterface.class);
        // Fetch and print a list of the contributors to this library.
        JsonElement contributors = toGet.getData("books","");

        //Parse The received Data
        String a =contributors.toString();

        JSONObject booksObj           = new JSONObject(a);
        JSONArray booksArray         = booksObj.getJSONArray("books");

        //Putting Recevied Data into a BookObject and Putting it into HashMap
        for(int i =0;i< booksArray.length();i++)
        {

            JSONObject temp          = new JSONObject(booksArray.get(i).toString());
            String title             = temp.get("title").toString() ;
            String author            = temp.get("author").toString();
            String categories        = temp.get("categories").toString();
            String lastCheckedOutby  = temp.get("lastCheckedOutBy").toString();
            String lastCheckedOut    = temp.get("lastCheckedOut").toString();
            String publisher      = temp.get("publisher").toString();
            String urls           = temp.get("url").toString();
            String id             = temp.get("id").toString();

            BookObject tempBook   = new BookObject(author,title,publisher,lastCheckedOut,lastCheckedOutby,categories,urls,id);
            AllBooks.put(tempBook.getId(), tempBook);
        }

        return AllBooks;
    }


    //Use this method to get a single book Using Retrofit@GET API
    public String deleteSingleBookFromServer(String bookToDelete) throws Exception {

        // Create a very simple REST adapter which points the Your Site API  endpoint.
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .build();

        // Create an instance of our DeleteInterface API interface.
        DeleteInterface toDelete = restAdapter.create(DeleteInterface.class);
        // Delete from Library.
        Response contributors = toDelete.deleteBook("books", bookToDelete);

        return contributors.toString();
    }


    //Use this method to get a single book Using Retrofit@GET API
    public String deleteAllBooksFromServer() throws Exception {

        // Create a very simple REST adapter which points the Your Site API  endpoint.
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .build();

        // Create an instance of our DeleteInterface API interface.
        DeleteInterface toDelete = restAdapter.create(DeleteInterface.class);
        // Delete from Library.
        Response contributors = toDelete.deleteBook("clean","");

        return contributors.toString();
    }

    private HashMap<String,BookObject> AllBooks = new HashMap<String, BookObject>();
}
