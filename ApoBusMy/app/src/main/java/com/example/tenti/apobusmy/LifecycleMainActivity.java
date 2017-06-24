package com.example.tenti.apobusmy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class LifecycleMainActivity extends AppCompatActivity {

  /**
   * The Tag for the Log
   */
  private static final String TAG_LOG = "LIFECYCLE";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Log.d(TAG_LOG, "ACTIVITY_B -> ON_CREATE");
  }

  @Override
  protected void onStart() {
    super.onStart();
    Log.d(TAG_LOG, "ACTIVITY_B -> ON_START");
  }

  @Override
  protected void onResume() {
    super.onResume();
    Log.d(TAG_LOG, "ACTIVITY_B -> ON_RESUME");
  }

  @Override
  protected void onPause() {
    super.onPause();
    Log.d(TAG_LOG, "ACTIVITY_B -> ON_PAUSE");
  }

  @Override
  protected void onStop() {
    super.onStop();
    Log.d(TAG_LOG, "ACTIVITY_B -> ON_STOP");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.d(TAG_LOG, "ACTIVITY_B -> ON_DESTROY");
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    Log.d(TAG_LOG, "ACTIVITY_B -> ON_RESTART");
  }

}
