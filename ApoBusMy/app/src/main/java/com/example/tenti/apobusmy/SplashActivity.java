package com.example.tenti.apobusmy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

public class SplashActivity extends Activity {

  /**
   * The Tag for the Log
   */
  private static final String TAG_LOG = SplashActivity.class.getName();

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
  private long mStartTime;

  /**
   * This variable is used to prevent the double launch of the next activity
   * or the launch when the current Activity has been closed.
   */
  private boolean mIsDone;

  /**
   * This is the Handler we use to manage time interval
   */
  private Handler mHandler = new Handler() {

    @Override
    public void handleMessage(Message msg) {
      switch (msg.what) {
        case GO_AHEAD_WHAT:
          // We calculate le elapsed time to prevent the early launch of the next
          // Activity
          long elapsedTime = SystemClock.uptimeMillis() - mStartTime;
          // We go ahead if not closed. We could use isDestroyed() but is available only
          // from API Level 17. Then we need to use an instance variable.
          if (elapsedTime >= MIN_WAIT_INTERVAL && !mIsDone) {
            mIsDone = true;
            goAhead();
          }
          break;
      }
    }

  };

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    View decorView = getWindow().getDecorView();
    int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    decorView.setSystemUiVisibility(uiOptions);
  }

  @Override
  protected void onStart() {
    super.onStart();
    // We set the time to consider as the start
    mStartTime = SystemClock.uptimeMillis();
    // We set the time for going ahead automatically
    final Message goAheadMessage = mHandler.obtainMessage(GO_AHEAD_WHAT);
    mHandler.sendMessageAtTime(goAheadMessage, mStartTime + MAX_WAIT_INTERVAL);
    Log.d(TAG_LOG, "Handler message sent!");
  }

  /**
   * Utility method that manages the transition to the FirstAccessActivity
   */
  private void goAhead() {
    // We create the explicit Intent
    final Intent intent = new Intent(this, MainActivity.class);
    // Launch the Intent
    startActivity(intent);
    // We finish the current Activity
    finish();
  }

}
