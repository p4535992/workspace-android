package com.github.p4535992.baseandroid01.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.p4535992.baseandroid01.R;
import com.github.p4535992.companymodel.CompanyModel;

public class CompanyModelActivity extends AppCompatActivity {

    /**
     * Tag for the Log
     */
    private static final String TAG = CompanyModelActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_model);
        final Intent srcIntent = getIntent();
        if (srcIntent != null) {
              /*
              // Usage of literals
              final String id = srcIntent.getStringExtra("id");
              final String name = srcIntent.getStringExtra("name");
              final String direction = srcIntent.getStringExtra("direction");
              final float latitude = srcIntent.getFloatExtra("latitude", 0.0f);
              final float longitude = srcIntent.getFloatExtra("longitude", 0.0f);
              */
              /*
              // Usage of the constants Keys
              final String id = srcIntent.getStringExtra(BusStop.Keys.ID);
              final String name = srcIntent.getStringExtra(BusStop.Keys.NAME);
              final String direction = srcIntent.getStringExtra(BusStop.Keys.DIRECTION);
              final float latitude = srcIntent.getFloatExtra(BusStop.Keys.LATITUDE, 0.0f);
              final float longitude = srcIntent.getFloatExtra(BusStop.Keys.LONGITUDE, 0.0f);
              final BusStop busStop = BusStop.Builder.create(id, name)
                      .withDirection(direction)
                      .withLocation(latitude, longitude)
                      .build();
                      */
              /*
              // Usage of the utility method
              final BusStop busStop = BusStop.fromIntent(srcIntent);
              */
              /*
              // In case of Serializable
              final BusStop busStop = (BusStop) srcIntent.getSerializableExtra(BusStop.Keys.ID);
              */
            // In case of Parcelable
            final CompanyModel busStop = (CompanyModel) srcIntent.getParcelableExtra(CompanyModel.Keys.ID);
            Log.d(TAG, "Received: " + busStop);
        }
    }
}
