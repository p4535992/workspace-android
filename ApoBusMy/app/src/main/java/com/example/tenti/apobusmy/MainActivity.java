package com.example.tenti.apobusmy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  /**
   * The request code for the bus stop pick
   */
  private static final int PICK_BUS_STOP_REQUEST_CODE = 1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    /*
    final Button sendBusStopButton = (Button) findViewById(R.id.send_bus_stop_button);
    sendBusStopButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // We send the data for the BusStop
        sendBusStopData();
      }
    });
    */
    /*
    findViewById(R.id.send_bus_stop_button).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // We send the data for the BusStop
        sendBusStopData();
      }
    });
    */
  }

  public void sendBusStopData1(final View view) {
    // We create a BudStop data
    final BusStop busStop = BusStop.Builder.create("myId", "myName")
            .withDirection("myDirection")
            .withLocation(51.5085300f, -0.1257400f)
            .build();
    // We create the Intent
    final Intent intent = new Intent(this, BusStopActivity.class);
    // We set the data
    intent.putExtra("id", busStop.id);
    intent.putExtra("name", busStop.name);
    intent.putExtra("direction", busStop.direction);
    intent.putExtra("latitude", busStop.latitude);
    intent.putExtra("longitude", busStop.longitude);
    // Launch the Activity
    startActivity(intent);
  }

  public void sendBusStopData2(final View view) {
    // We create a BudStop data
    final BusStop busStop = BusStop.Builder.create("myId", "myName")
            .withDirection("myDirection")
            .withLocation(51.5085300f, -0.1257400f)
            .build();
    // We create the Intent
    final Intent intent = new Intent(this, BusStopActivity.class);
    // We set the data
    intent.putExtra(BusStop.Keys.ID, busStop.id);
    intent.putExtra(BusStop.Keys.NAME, busStop.name);
    intent.putExtra(BusStop.Keys.DIRECTION, busStop.direction);
    intent.putExtra(BusStop.Keys.LATITUDE, busStop.latitude);
    intent.putExtra(BusStop.Keys.LONGITUDE, busStop.longitude);
    // Launch the Activity
    startActivity(intent);
  }

  public void sendBusStopData3(final View view) {
    // We create a BudStop data
    final BusStop busStop = BusStop.Builder.create("myId", "myName")
            .withDirection("myDirection")
            .withLocation(51.5085300f, -0.1257400f)
            .build();
    // We create the Intent
    final Intent intent = new Intent(this, BusStopActivity.class);
    // We set the data
    busStop.toIntent(intent);
    // Launch the Activity
    startActivity(intent);
  }

  public void sendBusStopData(final View view) {
    // We create a BudStop data
    final BusStop busStop = BusStop.Builder.create("myId", "myName")
            .withDirection("myDirection")
            .withLocation(51.5085300f, -0.1257400f)
            .build();
    // We create the Intent
    final Intent intent = new Intent(this, BusStopActivity.class);
    // We set the data
    intent.putExtra(BusStop.Keys.ID, busStop);
    // Launch the Activity
    startActivity(intent);
  }


  /**
   * Method invoked when we select the Button to pick a BusStop
   *
   * @param view The clicked View
   */
  public void pickBusStop(final View view) {
    // We create the Intent
    final Intent pickBusStopIntent = new Intent(BusStopListActivity.PICK_BUS_STOP_ACTION);
    // We pass the parameter for the location
    pickBusStopIntent.putExtra(BusStop.Keys.LATITUDE, 51.5085300f);
    pickBusStopIntent.putExtra(BusStop.Keys.LONGITUDE, -0.1257400f);
    // We launch the Intent
    startActivityForResult(pickBusStopIntent, PICK_BUS_STOP_REQUEST_CODE);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == PICK_BUS_STOP_REQUEST_CODE) {
      switch (resultCode) {
        case RESULT_OK:
          // We got the result
          final BusStop busStop = data.getParcelableExtra(BusStop.Keys.ID);
          Toast.makeText(this, "BusStop:" + busStop, Toast.LENGTH_SHORT).show();
          break;
        case RESULT_CANCELED:
          // Operation cancelled
          Toast.makeText(this, R.string.cancelled_operation, Toast.LENGTH_SHORT).show();
          break;
        default:
          // Other values
          Toast.makeText(this, R.string.custom_operation, Toast.LENGTH_SHORT).show();
      }
    }
  }
}
