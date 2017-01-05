package com.example.rahulkumarpandey.gasampleapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Rahul Kumar Pandey on 18-11-2016.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        findViewById(R.id.btnSecondScreen).setOnClickListener(this);
        findViewById(R.id.btnSendEvent).setOnClickListener(this);
        findViewById(R.id.btnException).setOnClickListener(this);
        findViewById(R.id.btnAppCrash).setOnClickListener(this);
        findViewById(R.id.btnLoadFragment).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSecondScreen:
                /**
                 * Launching another activity to track the other screen
                 */
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                break;

            case R.id.btnSendEvent:
                /**
                 * Event tracking
                 * Event(Category, Action, Label)
                 */
                // Tracking Event
                MyApplication.getInstance().trackEvent("Book", "Download", "Track event example");
                Toast.makeText(getApplicationContext(), "Event \'Book\' \'Download\' \'Event example\' is sent. Check it on Google Analytics Dashboard!", Toast.LENGTH_LONG).show();
                break;

            case R.id.btnAppCrash:
                /**
                 * Tracking App Crashes
                 * Manually generation app crash by dividing with zero
                 */
                Toast.makeText(getApplicationContext(), getString(R.string.toast_app_crash), Toast.LENGTH_LONG).show();

                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        int answer = 12 / 0;
                    }
                };

                Handler handler = new Handler();
                handler.postDelayed(runnable, 1500);
                break;

            case R.id.btnException:
                /**
                 * Tracking Exception Manually
                 * All known exceptions can be tracking this way
                 * using Try & Catch
                 */
                try {
                    String name = null;
                    if (name.equals("rahul")) {
                    /* Never comes here as it throws null pointer exception */
                    }
                } catch (Exception e) {
                    // Tracking exception
                    MyApplication.getInstance().trackException(e);
                    Toast.makeText(getApplicationContext(), getString(R.string.toast_track_exception), Toast.LENGTH_LONG).show();
                    Log.e(TAG, "Exception: " + e.getMessage());
                }
                break;

            case R.id.btnLoadFragment:
                /**
                 * Tracking Fragment View
                 */
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FooterFragment footerFragment = new FooterFragment();
                fragmentTransaction.replace(R.id.frame_container, footerFragment);
                fragmentTransaction.commit();
                break;
        }
    }
}
