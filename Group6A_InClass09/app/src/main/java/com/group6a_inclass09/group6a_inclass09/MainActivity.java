package com.group6a_inclass09.group6a_inclass09;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseObject;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginInterface {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ParseObject testObject = new ParseObject("TestObject");
//        testObject.put("foo", "bar");
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void gotoMessage() {

    }

    @Override
    public void gotoSignup() {
        getFragmentManager().beginTransaction()
                .addToBackStack(null)
                .replace(R.id.appRelative,new signUpFragment(),"Sigup Tag").commit();
    }
}
