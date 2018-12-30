package com.example.hatim.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("0HOMMsLMvKJcGwvjBx8S0Ko5PjYGmMIuo2rwWDMX")
                // if defined
                .clientKey("JgqI7xu3qe01nDfoRtuxLjWHpJfKvQpulUi2ypgu")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
    }

