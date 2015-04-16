package edu.gatech.ubicomp.falsetriggers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;


/**
 * Example shell activity which simply broadcasts to our receiver and exits.
 */
public class MyStubBroadcastActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        long[] vibrationPattern = {0, 500, 50, 300};
        //-1 - don't repeat
        final int indexInPatternToRepeat = -1;
        vibrator.vibrate(vibrationPattern, indexInPatternToRepeat);
//        Intent i = new Intent();
//        i.setAction("edu.gatech.ubicomp.falsetriggers.SHOW_NOTIFICATION");
//        i.putExtra(MyPostNotificationReceiver.CONTENT_KEY, getString(R.string.title));
//        sendBroadcast(i);
//        finish();
    }
}
