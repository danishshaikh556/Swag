package com.prolific.swag.app;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONException;

import java.util.ArrayList;
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
                    performGet.execute("");

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
            case R.id.action_add :   Intent i = new Intent(this, AddBooks.class);
                                     startActivity(i);
                                     return true;
            case R.id.action_seed:   toPass.clear();
                                     ServerCalls performFetch = new ServerCalls();
                                     performFetch.execute("");
                                    return true;
            default              :  return super.onOptionsItemSelected(item);
        }
    }
    /* this method is fired when an item is clicked */
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
         int idOfBook = v.getId();
        fireIntent(""+idOfBook);

    }

    //Fires Details activity
    private void fireIntent(String id)
    {
        if(AllBooks.containsKey(id))
        {
            Intent j = new Intent(this, Details.class);
            //Passing Data To display
            BookObject temp = AllBooks.get(id);
            j.putExtra("title",temp.getTitle());
            j.putExtra("author",temp.getAuthor());
            j.putExtra("publisher",temp.getPublisher());
            j.putExtra("lastCheckedOutBy",temp.getLastCheckedOutBy());
            j.putExtra("categories",temp.getCategories());
            j.putExtra("id",temp.getId());
            j.putExtra("lastCheckedOut",temp.getLastCheckedOut());
            j.putExtra("url",temp.getUrl());
            startActivity(j);
        }

    }

    //Called by doInBackGround()
    public void fillListView()
    {
        // add a ListView to fill the entire screen of the
        // ListActivity, and pass into it an ArrayAdapter
        // that manages the array of list items.
        // Can also pass it an android-created list item:
        //   android.R.layout.simple_list_item_1

        AdapterClass adapter =new AdapterClass(this,toPass);

        setListAdapter(adapter);

        //Get your ListView
        ListView myList = getListView();

        //Set on Click Listener
        myList.setOnItemClickListener(this);

    }


    public void HashMapToArrayList()
    {
        //Create an itterator to itterate hasmap to display
        //Required Data in the ListView
        Iterator<String> itr = AllBooks.keySet().iterator();

        while(itr.hasNext())
        {
            BookObject temp = AllBooks.get(itr.next());
            int idtoPass    = Integer.parseInt(temp.getId().toString());
            toPass.add(new ListDispRow(temp.getTitle().toString(), temp.getAuthor().toString(),idtoPass));
        }

    }




     ///Server INteraction
     //All tasks are async

    private class ServerCalls extends AsyncTask<String, String, String> {

        public void  doGet() throws Exception, JSONException {

            RetrofitAPICall getCall  = RetrofitAPICall.getInstance();
                            AllBooks =  getCall.getFromServerAllBooks();

                           HashMapToArrayList();

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

     }





    public HashMap<String,BookObject> AllBooks = new HashMap<String, BookObject>();
    public ArrayList<ListDispRow> toPass       = new ArrayList<ListDispRow>();


}