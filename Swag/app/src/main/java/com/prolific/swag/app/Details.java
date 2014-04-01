package com.prolific.swag.app;



/**
 * Created by Danish556 on 3/27/14.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;

public class Details extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //Displaying the title of the action Bar as "Books"
        android.app.ActionBar actionBar = getActionBar();
        actionBar.setTitle("Details");
         // Enabling Up / Back navigation
        actionBar.setDisplayHomeAsUpEnabled(true);

        ///Getting Extras from Intent passed in the previous activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
              book_details.setTitle(extras.getString("title"));
              book_details.setAuthor(extras.getString("author"));
              book_details.setPublisher(extras.getString("publisher"));
              book_details.setLastCheckedOutBy(extras.getString("lastCheckedOutBy"));
              book_details.setLastCheckedOut(extras.getString("lastCheckedOut"));
              book_details.setCategories(extras.getString("categories"));
              book_details.setId(extras.getString("id"));
              book_details.setUrl(extras.getString("url"));
        }

        layoutDisplay();


        //Instansiating the button CHECKOUT to set its onClick listner
        final Button checkout = (Button)findViewById(R.id.button_checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.button_checkout)
                {

                    //Upadte LastChecked by method
                    ServerCalls toPut =new ServerCalls();
                                toPut.execute(new String[]{""});

                }

            }
        });
    }


     @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.details, menu);

        // Set up ShareActionProvider's default share intent
        MenuItem shareItem = menu.findItem(R.id.action_share);
        sharingAction = (ShareActionProvider)MenuItemCompat.getActionProvider(shareItem);
        sharingAction.setShareIntent(getDefaultIntent());

        return true;
    }


    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id_item = item.getItemId();
        //Trigger the Delete Book Command
        if(id_item == R.id.action_delete)
        {
           //Dialog Box to confirm delete
            AlertDialog about = new AlertDialog.Builder(this).create();
            about.setTitle("---------DELETE---------");
            about.setMessage("Are You Sure you Want To Delete this Book!");
            about.setButton("YES", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // here you can add functions
                    ServerCalls server = new ServerCalls();
                                server.execute(new String[]{"delete"});
                }
            });
            about.setButton2("NO", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // here you can add functions
                }
            });
            about.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //Displays the screen Layout
    public void layoutDisplay()
    {
        TextView bookTitleDisp            = (TextView)findViewById(R.id.book_title);
        bookTitleDisp.setText("Title :" + book_details.getTitle().toString());
        TextView bookAuthorDisp           = (TextView)findViewById(R.id.book_author);
        bookAuthorDisp.setText("Author :" + book_details.getAuthor().toString());
        TextView bookLAstCheckDetailDisp  = (TextView)findViewById(R.id.book_checkout_details);
        bookLAstCheckDetailDisp.setText(book_details.getLastCheckedOutBy().toString());
        TextView bookPublisherDisp        = (TextView)findViewById(R.id.book_publisher);
        bookPublisherDisp.setText("Publisher :" + book_details.getPublisher().toString());
        TextView bookTagsDisp             = (TextView)findViewById(R.id.book_tags);
        bookTagsDisp.setText("Tags :"  + book_details.getCategories().toString());
    }


    //USed to transfer data when sharedAction Provider is clicked
    private Intent getDefaultIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,book_details.toString());
        return intent;
    }


    //Returns Time in format yyyy-MM-dd HH:mm:ss zzz
    private String getTime()
    {
        //Date and Time for Last Checked in Checkout
        //SingleTon Design Pattern implemented here
        Calendar calendar = Calendar.getInstance();
        int year       = calendar.get(Calendar.YEAR);
        int month      = calendar.get(Calendar.MONTH);
        int day        = calendar.get(Calendar.DAY_OF_MONTH);
        int hours      = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes    = calendar.get(Calendar.MINUTE);
        int seconds    = calendar.get(Calendar.SECOND);
        TimeZone times = calendar.getTimeZone();
        String  timeSDT= times.getDisplayName(false,times.SHORT);
        String frame   = ""+ year+":"+month +":"+ day+ ":"+hours+":"+minutes+":"+seconds+":"+timeSDT;

        return frame;
    }

    ///Server INteraction
    //All tasks are async
     private class ServerCalls extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params)  {
            //Get Instance of call
           RetrofitAPICall toUpdate =RetrofitAPICall.getInstance();

            String toReturn="";

            try{
            //Make delete Single book call to server
            if(params[0].equals("delete"))
            {
                 toReturn = toUpdate.deleteSingleBookFromServer(book_details.getId().toString());

            }else{
               //Make a put RequestTo the server
                //Get Time to PUT request to server
                 toReturn           = getTime();
                 String data        =toUpdate.putOnServer(book_details.getId().toString(),toReturn,"Zidane");
            }
            }catch(Exception e){e.printStackTrace();}


                   return toReturn;
       }

        @Override
        protected void onPostExecute(String result) {

            Context context = getApplicationContext();
            //Toast for User Update
            Toast.makeText(context, "Update at :" + result, Toast.LENGTH_SHORT).show();
            finish();
         }
     }




    private ShareActionProvider sharingAction;
    private BookObject          book_details   =new BookObject();
}

