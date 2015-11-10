package com.group6a_inclass09.group6a_inclass09;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Arunkumar's on 11/9/2015.
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "wWu9Arz2YhejpA42tEwdbbJMErPdhZ2bZv1aFoon", "nagmSG7im7nI7maMz66aMmgiGbtHPfdTQeeCxkDA");

    }
}
