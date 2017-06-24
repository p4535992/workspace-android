package com.github.p4535992.baseandroid01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_LOG = MainActivity.class.getName();

    //OVVERRRIDES METHODS

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //settiamo la nostra view
        setContentView(R.layout.activity_splash);
        View decorView = getWindow().getDecorView();
        int visibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(visibility);
        Log.d(TAG_LOG, "ACTIVITY -> ON_CREATE");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG_LOG, "ACTIVITY -> ON_START");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG_LOG, "ACTIVITY -> ON_RESUME");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG_LOG, "ACTIVITY -> ON_PAUSE");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG_LOG, "ACTIVITY -> ON_STOP");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG_LOG, "ACTIVITY -> ON_DESTROY");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG_LOG, "ACTIVITY -> ON_RESTART");
    }


    //OTHER METHODS

    //Method invoked when we select the Button to pick a BusStop
    //NOte is launched from a onClick on layout
    /*
    public void pickBusStop(final View view) {
        // We create the Intent
        final Intent pickBusStopIntent = new Intent(BusStopListActivity.PICK_BUS_STOP_ACTION);
        // We pass the parameter for the location
        pickBusStopIntent.putExtra(BusStop.Keys.LATITUDE, 51.5085300f);
        pickBusStopIntent.putExtra(BusStop.Keys.LONGITUDE, -0.1257400f);
        // We launch the Intent (call the noStartActivytResult ovverride method)
        startActivityForResult(pickBusStopIntent, PICK_BUS_STOP_REQUEST_CODE);
    }
    */
}
