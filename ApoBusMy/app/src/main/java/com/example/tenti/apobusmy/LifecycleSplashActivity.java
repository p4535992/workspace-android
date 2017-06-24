package com.example.tenti.apobusmy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class LifecycleSplashActivity extends Activity {

  /**
   * The Tag for the Log
   */
  private static final String TAG_LOG = "LIFECYCLE";

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    View decorView = getWindow().getDecorView();
    int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    decorView.setSystemUiVisibility(uiOptions);
    Log.d(TAG_LOG, "ACTIVITY_A -> ON_CREATE");
    // We get the reference of the ImageView to manage the touch event
    final ImageView logoImageView = (ImageView) findViewById(R.id.splash_imageview);
    logoImageView.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View view, MotionEvent motionEvent) {
        // We create the explicit Intent
        final Intent intent = new Intent(LifecycleSplashActivity.this, LifecycleMainActivity.class);
        // Launch the Intent
        startActivity(intent);
        return false;
      }
    });
  }

  @Override
  protected void onStart() {
    super.onStart();
    Log.d(TAG_LOG, "ACTIVITY_A -> ON_START");
  }

  @Override
  protected void onResume() {
    super.onResume();
    Log.d(TAG_LOG, "ACTIVITY_A -> ON_RESUME");
  }

  @Override
  protected void onPause() {
    super.onPause();
    Log.d(TAG_LOG, "ACTIVITY_A -> ON_PAUSE");
  }

  @Override
  protected void onStop() {
    super.onStop();
    Log.d(TAG_LOG, "ACTIVITY_A -> ON_STOP");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.d(TAG_LOG, "ACTIVITY_A -> ON_DESTROY");
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    Log.d(TAG_LOG, "ACTIVITY_A -> ON_RESTART");
  }

}
