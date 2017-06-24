package com.github.p4535992.baseandroid01.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.p4535992.baseandroid01.Const;
import com.github.p4535992.baseandroid01.R;
import com.github.p4535992.companymodel.CompanyModel;

public class CompanyModelListActivity extends AppCompatActivity {

    /**
     * The Action to use when we want to select a BusStop
     */
    public static final String PICK_ACTION = Const.PKG + ".activity" + ".action.PICK_COMPANY_MODEL_ACTION";

    private static final int PICK_REQUEST_CODE = 1;

    private String mName;

    private String mAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_model_list);
        // We get the Intent
        final Intent inputIntent = getIntent();
        if (inputIntent != null) {
            // We read input parameters
            mName = inputIntent.getStringExtra(CompanyModel.Keys.NAME);
            mAddress = inputIntent.getStringExtra(CompanyModel.Keys.ADDRESS);
            if (mName != null && !mName.isEmpty() && mAddress != null && !mAddress.isEmpty()) {
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

    //Questo metodo è deprecato infavore della azioni sui layout e l'utilizzo
    //setResult sull'activity chiamata
    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_REQUEST_CODE){
            switch (resultCode){
                case Activity.RESULT_OK:
                    //l'azione dell'activity è andata a buon fine
                    break;
                case Activity.RESULT_CANCELED:
                    //cliccato su back o distrutta la activity
                    break;
                case Activity.RESULT_FIRST_USER:
                    //Valore da cui assegnare i propri valori customizzati
                    break;
                default:
                    break;
            }
        }
    }
    */

    /**
     * We send the data back, replace the onActivtyResulrBack ovverride methods
     *
     * @param view The selected Button
     */
    public void sendBusStopBack(final View view) {
        final CompanyModel busStop = CompanyModel.Builder
                .create(1, "myName","myAddress",0).build();
        // The return intent
        final Intent backIntent = new Intent();
        backIntent.putExtra(CompanyModel.Keys.ID, busStop);
        // We send the result back
        setResult(RESULT_OK, backIntent);
        finish();
    }

}
