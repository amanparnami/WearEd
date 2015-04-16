package edu.gatech.ubicomp.falsetriggers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Aman Parnami on 4/16/15.
 */
public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
        Log.d("TEST", "Clear app processing here");
    }
}