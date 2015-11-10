package com.group6a_inclass09.group6a_inclass09;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginInterface, signUpFragment.SignUpInterface {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseUser user = new ParseUser();
        user.setUsername("bob");
        user.setPassword("123");
        user.setEmail("bob@g.com");
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e==null)
                    Log.d("demo","Cool");
                else Log.d("demo","Excep");
            }
        });
//        ParseObject testObject = new ParseObject("Messages");
//        testObject.put("message", "bar");
//        testObject.put("Created By", "bar");
//        testObject.saveInBackground();

        ParseUser checkUser = ParseUser.getCurrentUser();

        if (checkUser!=null){
            //Go for messaging fragment
        }
        else {
            getFragmentManager().beginTransaction()
                    .add(R.id.appRelative, new LoginFragment(), "Login Tag").commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

    @Override
    public void gotoMessage() {
        getFragmentManager().beginTransaction()
                .replace(R.id.appRelative, new messagesFragment(), "Message Tag").commit();
    }

    @Override
    public void gotoSignup() {
        getFragmentManager().beginTransaction()
                .addToBackStack(null)
                .replace(R.id.appRelative, new signUpFragment(), "Sigup Tag").commit();
    }

    public void refreshOnClick (MenuItem aItem){

    }

    public void composeOnClick (MenuItem aItem){

    }

    public void logoutOnClick (MenuItem aItem){

    }

    @Override
    public void goToLogin() {
        getFragmentManager().popBackStack();
    }
}
