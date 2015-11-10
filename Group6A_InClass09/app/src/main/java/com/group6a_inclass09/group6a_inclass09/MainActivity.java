package com.group6a_inclass09.group6a_inclass09;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
/*Michael Vitulli - Arunkumar Bagavathi*/
public class MainActivity extends AppCompatActivity implements LoginFragment.LoginInterface, signUpFragment.SignUpInterface,composeMessageFragment.composeInterface {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseUser checkUser = ParseUser.getCurrentUser();

        if (checkUser!=null){
            //Go for messaging fragment
            gotoMessage();
        }
        else {
            getFragmentManager().beginTransaction()
                    .add(R.id.appRelative, new LoginFragment(), "Login Tag").commit();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        //getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//
//
//        return super.onOptionsItemSelected(item);
//    }

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
        getFragmentManager().beginTransaction()
                .addToBackStack(null)
                .replace(R.id.appRelative, new composeMessageFragment(), "Compose Tag").commit();
    }

    public void logoutOnClick (MenuItem aItem){
        ParseUser.logOutInBackground(new LogOutCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    getFragmentManager().beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.appRelative, new LoginFragment(), "Login Tag").commit();

                }
            }
        });
    }

    @Override
    public void goToLogin() {
        getFragmentManager().popBackStack();
    }

    @Override
    public void gotoCompose(String msg) {
        ParseObject fParseObj = new ParseObject("Messages");
        fParseObj.put("createdBy", ParseUser.getCurrentUser());
        fParseObj.put("message", msg);

        fParseObj.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {

//                    MessageActivity.userMessages.add(fParseObj);
//                    MessageActivity.adapter.notifyDataSetChanged();
//                    Toast.makeText(ComposeMessage.this, "Message Saved", Toast.LENGTH_SHORT).show();
//                    finish();
                    getFragmentManager().popBackStack();
                }
                else Toast.makeText(MainActivity.this, "Message not sent!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
