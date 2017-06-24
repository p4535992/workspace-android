package com.example.tenti.apobusmy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * This is the Activity we use to select a specific BusStop from a list
 */
public class BusStopListActivity extends AppCompatActivity {

  /**
   * The Action to use when we want to select a BusStop
   */
  public static final String PICK_BUS_STOP_ACTION = Const.PKG + ".action.PICK_BUS_STOP_ACTION";

  private float mLatitude;

  private float mLongitude;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bus_stop_list);
    // We get the Intent
    final Intent inputIntent = getIntent();
    if (inputIntent != null) {
      // We read input parameters
      mLatitude = inputIntent.getFloatExtra(BusStop.Keys.LATITUDE, 0.0f);
      mLongitude = inputIntent.getFloatExtra(BusStop.Keys.LONGITUDE, 0.0f);
      if (mLatitude != 0.0f && mLongitude != 0.0f) {
        // We do something with the data.
      } else {
        // We finish the Activity
        finish();
      }
    } else {
      // We finish the Activity
      finish();
    }
  }

  /**
   * We send the data back
   *
   * @param view The selected Button
   */
  public void sendBusStopBack(final View view) {
    final BusStop busStop = BusStop.Builder.create("myId", "myName")
            .withLocation(mLatitude, mLongitude)
            .withDirection("my direction")
            .build();
    // The return intent
    final Intent backIntent = new Intent();
    backIntent.putExtra(BusStop.Keys.ID, busStop);
    // We send the result back
    setResult(RESULT_OK, backIntent);
    finish();
  }
}
