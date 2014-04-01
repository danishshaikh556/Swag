package com.prolific.swag.app;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Danish556 on 3/27/14.
 */




public class AddBooks extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbooks);

        //Displaying the title of the action Bar as "Books"
        android.app.ActionBar actionBar = getActionBar();
        actionBar.setTitle("Add Data");
        // Enabling Up / Back navigation
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Instansiating the button to set its onClick listner
        final Button submit = (Button)findViewById(R.id.submit);
               submit.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       if(view.getId() == R.id.submit)
                       {
                           ServerCalls PostToServer =new ServerCalls();
                                       PostToServer.execute("");
                       }

                   }
               });
   }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.addbooks, menu);

        return true;
    }


    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id){

            case R.id.action_done :  finish();
                                     return true;
            default               :  return super.onOptionsItemSelected(item);
        }

    }

    public BookObject getPostRequestBody()
    {
        BookObject temp           =   new BookObject();
        EditText title_add        =  (EditText)findViewById(R.id.addbookTitle);
                                      temp.setTitle(title_add.getText().toString());
        EditText author_add       =  (EditText)findViewById(R.id.addauthor);
                                      temp.setAuthor(author_add.getText().toString());
        EditText publisher_add    =  (EditText)findViewById(R.id.addpublisher);
                                      temp.setPublisher(publisher_add.getText().toString());
        EditText tags_add         =  (EditText)findViewById(R.id.addcategories);
                                      temp.setCategories(tags_add.getText().toString());
        return temp;

    }

    //Daisplays Toast
    public void makeToast(String toast){
    //Show user Confirmation
        Context context = getApplicationContext();
        Toast.makeText(context,"Book Added:-"+ toast , Toast.LENGTH_SHORT).show();
 }

    ///Server INteraction
    //All tasks are async
    private class ServerCalls extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params)  {

            //Get Instance of call
            RetrofitAPICall toPost        = RetrofitAPICall.getInstance();

            //Get Format of Book To Post
            BookObject bookToPostToServer = getPostRequestBody();

            //Make a POST RequestTo the server
            try{
            String reveived               = toPost.postBooksToServer(bookToPostToServer);
            }catch(Exception e){}
            return bookToPostToServer.toString();
        }

        @Override
        protected void onPostExecute(String result) {makeToast(result);finish();}
    }






}
