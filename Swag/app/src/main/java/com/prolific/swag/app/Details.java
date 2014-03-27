package com.prolific.swag.app;

/**
 * Created by Danish556 on 3/27/14.
 */


        import android.support.v7.app.ActionBarActivity;
        import android.support.v7.app.ActionBar;
        import android.support.v4.app.Fragment;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;
        import android.os.Build;
        import android.widget.TextView;

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





    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id){
            /*
            case R.id.action_add :  dan.setText("Add Button Pressed");
                                    return true;
            case R.id.action_seed:  dan.setText("Seed Button Pressed");
                                    return true;
                                    */
            default             :  return super.onOptionsItemSelected(item);
        }

    }

}

