package edu.gatech.ubicomp.weared;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.SendCallback;


public class StudentActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        final Button bUnclear = (Button) findViewById(R.id.buttonUnclear);
        final Button bUnderstand = (Button) findViewById(R.id.buttonUnderstand);
        bUnclear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendUnclear();
            }
        });
        bUnderstand.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendUnderstood();
            }
        });

        ParsePush.unsubscribeInBackground("Professor");
        ParsePush.subscribeInBackground("Student");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student, menu);
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

    public void sendUnclear() {
        ParsePush push = new ParsePush();
        ParseQuery query = ParseInstallation.getQuery();
        query.whereEqualTo("channels", "Professor");
        push.setQuery(query);
        push.setMessage("Something is unclear!");
        push.sendInBackground(new SendCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("push", "success!");
                } else {
                    Log.d("push", "failure");
                }
            }
        });
    }

    public void sendUnderstood() {
        ParsePush push = new ParsePush();
        ParseQuery query = ParseInstallation.getQuery();
        query.whereEqualTo("channels", "Professor");
        push.setQuery(query);
        push.setMessage("Someone gets it now!");
        push.sendInBackground(new SendCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("push", "success!");
                } else {
                    Log.d("push", "failure");
                }
            }
        });
    }
}
