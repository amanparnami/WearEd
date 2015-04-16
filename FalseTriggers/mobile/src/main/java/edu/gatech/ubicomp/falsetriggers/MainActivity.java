package edu.gatech.ubicomp.falsetriggers;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Arrays;


public class MainActivity extends Activity implements Handler.Callback {

    public ImageButton breaktimeButton, repeatButton, slowdownButton, cancelButton, questionButton;
    private static final int MSG_POST_NOTIFICATIONS = 0;
    private static final long POST_NOTIFICATIONS_DELAY_MS = 200;
    public static final String CONTENT_KEY = "contentText";

    public static final int BREAKTIME_CODE = 0;
    public static final int REPEAT_CODE = 1;
    public static final int CANCEL_CODE = 2;
    public static final int SLOWDOWN_CODE = 3;
    public static final int QUESTION_CODE = 4;


    private Handler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler = new Handler(this);

        breaktimeButton = (ImageButton) findViewById(R.id.breaktimeButton);
        questionButton = (ImageButton) findViewById(R.id.questionButton);
        repeatButton = (ImageButton) findViewById(R.id.repeatButton);
        slowdownButton = (ImageButton) findViewById(R.id.slowdownButton);
        cancelButton = (ImageButton) findViewById(R.id.cancelButton);

        breaktimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postNotifications(BREAKTIME_CODE);
            }
        });

        questionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postNotifications(QUESTION_CODE);
            }
        });

        repeatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postNotifications(REPEAT_CODE);
            }
        });

        slowdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postNotifications(SLOWDOWN_CODE);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postNotifications(CANCEL_CODE);
            }
        });
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

    /**
     * Post the sample notification(s) using current options.
     */
    private void postNotifications(int code) {

//        Intent displayIntent = new Intent(this);
        Intent deleteIntent = new Intent(this, NotificationReceiver.class);
        deleteIntent.setAction("delete");

        final NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);


        // This notification will be shown only on phone
//        final NotificationCompat.Builder phoneNotificationBuilder = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle("Title phone")
//                .setContentText("Text phone")
//                .setOngoing(true)
//                .setOnlyAlertOnce(true)
//                .setGroup("GROUP")
//                .setGroupSummary(true);

        String title = "";
        String content = "";

        switch(code)
        {
            case QUESTION_CODE:
                title = "question";
                content = "I have a question.";
                break;
            case SLOWDOWN_CODE:
                title = "slowdown";
                content = "I am lost.";
                break;
            case REPEAT_CODE:
                title = "repeat";
                content = "Please repeat.";
                break;
            case CANCEL_CODE:
                NotificationManagerCompat.from(this).cancelAll();
                break;
            case BREAKTIME_CODE:
                title = "break";
                content = "Can we take a break?";
                break;
        }
        // This notification will be shown only on watch
        if(code != CANCEL_CODE) {
            final NotificationCompat.Builder wearableNotificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(title)
                    .setContentText(content)
                    .setOngoing(false)
//                .setOnlyAlertOnce(true)
//                .setGroup("GROUP")
//                .setGroupSummary(false)
                    .setVibrate(new long[] { 100, 1000, 1000})
                    .setDeleteIntent(PendingIntent.getBroadcast(this, 0, deleteIntent, 0));

            //notificationManager.notify(0, phoneNotificationBuilder.build());
            notificationManager.notify(1, wearableNotificationBuilder.build());
        }


//        Notification notification = new NotificationCompat.Builder(getApplicationContext())
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle("Hello")
//                .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
//                .build();
//
//        NotificationManagerCompat.from(this).notify(0, notification);
    }

    @Override
    public boolean handleMessage(Message message) {
//        switch (message.what) {
//            case MSG_POST_NOTIFICATIONS:
//                postNotifications();
//                return true;
//        }
        return false;
    }
}
