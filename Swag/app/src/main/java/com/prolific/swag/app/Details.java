package com.prolific.swag.app;

/**
 * Created by Danish556 on 3/27/14.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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


        //Instansiating the button CHECKOUT to set its onClick listner
        final Button checkout = (Button)findViewById(R.id.button_checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.button_checkout)
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
                    Toast.makeText(Details.this, frame, Toast.LENGTH_SHORT).show();
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
         return super.onOptionsItemSelected(item);
        }



    private Intent getDefaultIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,"Book Details");
        return intent;
    }

    private ShareActionProvider sharingAction;
}

