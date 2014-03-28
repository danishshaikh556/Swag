package com.prolific.swag.app;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Books extends ListActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       //Displaying the title of the action Bar as "Books"
        android.app.ActionBar actionBar = getActionBar();
        actionBar.setTitle("Books");

        // add a ListView to fill the entire screen of the
        // ListActivity, and pass into it an ArrayAdapter
        // that manages the array of list items.
        // Can also pass it an android-created list item:
        //   android.R.layout.simple_list_item_1
        setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_books, COLORS));

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
            case R.id.action_add :  LocationFound();
                                    return true;
            case R.id.action_seed:   return true;
            default              :  return super.onOptionsItemSelected(item);
        }
    }
    private void LocationFound() {
        Intent i = new Intent(this, AddBooks.class);
        startActivity(i);
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


    // define a list of colors that the list will display
    static final String[] COLORS = new String[] {
            "Red",
            "Orange",
            "Yellow",
            "Green",
            "Blue",
            "Indigo",
            "Violet"
    };

}
