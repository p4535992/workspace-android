package com.github.p4535992.baseandroid01;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.github.p4535992.util.view.OnSwipeTouchListener;
import com.github.p4535992.util.view.ViewUtility;

import java.lang.ref.WeakReference;

public class SplashActivity extends AppCompatActivity {

    /**
     * The Tag for the Log
     */
    private static final String TAG_LOG = SplashActivity.class.getName();

    /**
     * THe key to use to save the isDone state variable
     */
    private static final String IS_DONE_KEY = Const.PKG + ".key.IS_DONE_KEY";

    /**
     * THe key to use to save the mStartTime state variable
     */
    private static final String START_TIME_KEY = Const.PKG + ".key.START_TIME_KEY";

    /**
     * The minimum interval we have to wait before going ahead
     */
    private static final long MIN_WAIT_INTERVAL = 1500L;

    /**
     * The maximum interval we can wait before going ahead
     */
    private static final long MAX_WAIT_INTERVAL = 3000L;

    /**
     * The What to use into the Handler to go to the next Activity
     */
    private static final int GO_AHEAD_WHAT = 1;

    /**
     * The time we consider as the start
     */
    private long mStartTime = -1L;

    /**
     * This variable is used to prevent the double launch of the next activity
     * or the launch when the current Activity has been closed.
     */
    private boolean mIsDone;

    /**
     *
     */
    private GestureDetector mGestureDetector;

    /**
     * This is the Handler we use to manage time interval
     */
    private UiHandler mHandler;

    /**
     * This is a static class that doesn't retain the reference to the container class thought
     * the this reference. The reference to the container Activity is make through the
     * WeakReference that is not counted as a valid reference by the GC
     */
    private static class UiHandler extends Handler {

        /**
         * The WeakReference to the Activity that uses it
         */
        private WeakReference<SplashActivity> mActivityRef;

        /**
         * Constructor with the sourceActivity reference
         *
         * @param srcActivity The Activity we need a reference of.
         */
        private UiHandler(final SplashActivity srcActivity) {
            // We just save the reference to the src Activity
            this.mActivityRef = new WeakReference<SplashActivity>(srcActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            // We get the reference to the sourceActivity
            final SplashActivity srcActivity = this.mActivityRef.get();
            if (srcActivity == null) {
                // In this case the reference to the Activity is lost. It should not be
                // the case.
                Log.d(TAG_LOG, "Reference to NoLeakSplashActivity lost!");
                return;
            }
            switch (msg.what) {
                case GO_AHEAD_WHAT:
                    // We calculate le elapsed time to prevent the early launch of the next
                    // Activity
                    long elapsedTime = SystemClock.uptimeMillis() - srcActivity.mStartTime;
                    // We go ahead if not closed. We could use isDestroyed() but is available only
                    // from API Level 17. Then we need to use an instance variable.
                    if (elapsedTime >= MIN_WAIT_INTERVAL && !srcActivity.mIsDone) {
                        srcActivity.mIsDone = true;
                        srcActivity.goAhead(MainActivity.class);
                    }
                    break;
            }
        }

    }
    
    

    /**
     * Utility method that manages the transition to the FirstAccessActivity
     */
    private void goAhead(Class<?> cls) {
        // We create the explicit Intent
        final Intent intent = new Intent(this, cls);
        // Launch the Intent
        startActivity(intent);
        // We finish the current Activity
        finish();
    }

    ///////////////////////////////
    // Ovverrides methods Activity
    ///////////////////////////////
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // Implicit way to launch the service
        //startService(ServiceUtil.SYNC_INTENT);
        // Explicit way
        //startService(ServiceUtil.getExplicitSyncIntent(this));
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
        Log.d(TAG_LOG, "ACTIVITY -> ON_CREATE");
        // If present we get the information about the startTime
        if (savedInstanceState != null) {
            this.mStartTime = savedInstanceState.getLong(START_TIME_KEY);
        }
        // We initialize the Handler
        mHandler = new UiHandler(this);
        // We get the reference of the ImageView to manage the touch event
        final ImageView logoImageView = ViewUtility.findViewById(this, R.id.splash_imageview);
        /*
        logoImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d(TAG_LOG, "ImageView touched!!");
                long elapsedTime = SystemClock.uptimeMillis() - mStartTime;
                if (elapsedTime >= MIN_WAIT_INTERVAL && !mIsDone) {
                    mIsDone = true;
                    //goAhead();
                    // We create the explicit Intent
                    final Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    // Launch the Intent
                    startActivity(intent);
                } else {
                    Log.d(TAG_LOG, "Too early!");
                }
                return false;
            }
        });
        */

        logoImageView.setOnTouchListener(new OnSwipeTouchListener(SplashActivity.this) {
            @Override
            public void onClick() {
                super.onClick();
                // your on click here
            }

            @Override
            public void onDoubleClick() {
                super.onDoubleClick();
                // your on onDoubleClick here
            }

            @Override
            public void onLongClick() {
                super.onLongClick();
                // your on onLongClick here
            }

            @Override
            public void onSwipeUp() {
                super.onSwipeUp();
                // your swipe up here
            }

            @Override
            public void onSwipeDown() {
                super.onSwipeDown();
                // your swipe down here.
            }

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                // your swipe left here.
            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                // your swipe right here.
            }

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                Log.d(TAG_LOG, "ImageView touched!!");
                long elapsedTime = SystemClock.uptimeMillis() - mStartTime;
                if (elapsedTime >= MIN_WAIT_INTERVAL && !mIsDone) {
                    mIsDone = true;
                    //goAhead();
                    // We create the explicit Intent
                    final Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    // Launch the Intent
                    startActivity(intent);
                } else {
                    Log.d(TAG_LOG, "Too early!");
                }
                return false;


            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG_LOG, "ACTIVITY -> ON_START");
        // We set the time to consider as the start
        //mStartTime = SystemClock.uptimeMillis();
        if (mStartTime == -1L) {
            // We set the time to consider as the start only if not already done
            // in the onStart() method
            mStartTime = SystemClock.uptimeMillis();
        }
        // We set the time for going ahead automatically
        final Message goAheadMessage = mHandler.obtainMessage(GO_AHEAD_WHAT);
        mHandler.sendMessageAtTime(goAheadMessage, mStartTime + MAX_WAIT_INTERVAL);
        Log.d(TAG_LOG, "Handler message sent!");
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
        //svuota la coda dei messaggi quando l'attività è distrutta
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG_LOG, "ACTIVITY -> ON_RESTART");
    }

    /**
     * Metodo invocato subito prima della distruzione dell'attività
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // We save the state of the startTime value
        outState.putBoolean(IS_DONE_KEY, mIsDone);
        // We save the state of the isDone value
        outState.putLong(START_TIME_KEY, mStartTime);
    }

    /**
     * Metodo invocato subito prima della creazione dell'attività
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // We restore the value of the isDone variable
        this.mIsDone = savedInstanceState.getBoolean(IS_DONE_KEY);
    }
}
