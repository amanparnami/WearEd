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


public class ProfessorActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);

        final Button button = (Button) findViewById(R.id.buttonSend);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendPing();
            }
        });

        ParsePush.unsubscribeInBackground("Student");
        ParsePush.subscribeInBackground("Professor");
    }

    public void sendPing() {
        ParsePush push = new ParsePush();
        ParseQuery query = ParseInstallation.getQuery();
        query.whereEqualTo("channels", "Student");
        push.setQuery(query);
        push.setMessage("Pay Attention!");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_professor, menu);
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
}
