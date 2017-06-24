package com.github.p4535992.util.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.github.p4535992.util.R;

/**
 * Example of an AlertDialog with Fragment
 */
public class CustomAlertDialog extends DialogFragment {

  /**
   * Callback interface that the listening activity should implement to receive information
   * from the AlertDialog
   */
  public interface AlertDialogListener {

    /**
     * Pressed when user press yes
     */
    void yesPressed();

    /**
     * Pressed when user press no
     */
    void noPressed();

  }

  /*
   * The Listener of the  AlertDialog
   */
  private OnClickListener mOnClickListener;

  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    // if the Activity implements the listener interface we save its reference
    if (activity instanceof AlertDialogListener) {
      final AlertDialogListener listener = (AlertDialogListener) activity;
      mOnClickListener = new OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
          switch (which) {
            case Dialog.BUTTON_POSITIVE:
              listener.yesPressed();
              break;
            case Dialog.BUTTON_NEGATIVE:
              listener.noPressed();
              break;
            default:
              // Not managed
              break;
          }

        }

      };
    }
  }

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    // Here we create and return the AlertDialog using a Builder
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setIcon(R.drawable.ic_launcher)
            .setTitle(R.string.alert_dialog_title)
            .setPositiveButton(R.string.yes_label, mOnClickListener)
            .setNegativeButton(R.string.no_label, mOnClickListener);

    return builder.create();
  }

  //USAGE
  /*
  public class MainActivity extends FragmentActivity
        implements CustomAlertDialog.AlertDialogListener {

    // The tag for the Fragment for AlertDialog into the layout
    private static final String ALERT_DIALOG_TAG = "ALERT_DIALOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
    }

    // Called when we press on the alertDialog button
    // @param alertDialogButton The button
    public void showAlertDialog(View alertDialogButton) {
      CustomAlertDialog alertDialog = new CustomAlertDialog();
      alertDialog.show(getSupportFragmentManager(), ALERT_DIALOG_TAG);
    }

    @Override
    public void yesPressed() {
      Toast.makeText(this, "YES PRESSED", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void noPressed() {
      Toast.makeText(this, "NO PRESSED", Toast.LENGTH_SHORT).show();
    }


  }
   */

}
