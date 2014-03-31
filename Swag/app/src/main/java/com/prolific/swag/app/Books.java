package com.prolific.swag.app;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Iterator;

public class Books extends ListActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Displaying the title of the action Bar as "Books"
        android.app.ActionBar actionBar = getActionBar();
        actionBar.setTitle("Books");

        ServerCalls performGet = new ServerCalls();
                    performGet.execute("","","");

  }

    //Called by doInBackGround()
   public void fillListView()
   {
       // add a ListView to fill the entire screen of the
       // ListActivity, and pass into it an ArrayAdapter
       // that manages the array of list items.
       // Can also pass it an android-created list item:
       //   android.R.layout.simple_list_item_1

       setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_books, toPass));
       // obtain the ListView that was created by setListAdapter()
       ListView myList = getListView();
       // allow us to filter the list with keypresses
       myList.setTextFilterEnabled(true);
      // implement an onClick listener for when a user taps a color
       myList.setOnItemClickListener(this);

   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.books, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id){
            case R.id.action_add :  Intent i = new Intent(this, AddBooks.class);
                                    startActivity(i);
                                    return true;
            case R.id.action_seed:  Intent j = new Intent(this, Details.class);
                                    startActivity(j);
                                    return true;
            default              :  return super.onOptionsItemSelected(item);
        }
    }




    /* display a Toast with message text. */
    private void showMessage(CharSequence text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    /* this method is fired when an item is clicked */
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        TextView item = (TextView)v;
        showMessage(item.getText());
    }

     ///Server INteraction
     //All tasks are async

    private class ServerCalls extends AsyncTask<String, String, String> {

        public void  doGet() throws Exception, JSONException {

            RetrofitAPICall getCall  = new RetrofitAPICall();
                            AllBooks =  getCall.getFromServerAllBooks();

            //Compatible for ListView
            int stringArraySize = AllBooks.keySet().size();
                         toPass = new String[stringArraySize];

            //Create an itterator to itterate hasmap to display
            //Required Data in the ListView
             Iterator<String> itr = AllBooks.keySet().iterator();

            int index =0;
            while(itr.hasNext())
            {
                toPass[index++]=AllBooks.get(itr.next()).getId();
            }

        }


        @Override
        protected String doInBackground(String... params)  {
            String toReturn ="";
            try {
                doGet();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return toReturn;
        }

        @Override
        protected void onPostExecute(String result) {
                fillListView();
          }
        @Override
        protected void onPreExecute() {}
        @Override
        protected void onProgressUpdate(String... text) {}


    }





    public HashMap<String,BookObject> AllBooks ;
    public String[]          toPass            ;


}