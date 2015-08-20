package edu.gatech.ubicomp.weared;

import android.app.Application;
import android.content.Intent;

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;

/**
 * Created by Peter on 4/1/2015.
 */
public class WearEdApp extends Application {
    @Override
    public void onCreate() {
        Parse.initialize(this, "HwcfLm5eP2Ng6Empu0X8qkw9kqDWPZcS970q9Wp7", "fFwoZDxf8oAZx1umrUvmYcrd79Gm1udjyd87HpTc");
        ParseInstallation.getCurrentInstallation().saveInBackground();
        ParseAnalytics.trackAppOpenedInBackground(new Intent());
    }
}
