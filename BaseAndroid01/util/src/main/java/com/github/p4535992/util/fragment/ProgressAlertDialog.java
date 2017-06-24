package com.github.p4535992.util.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;

import com.github.p4535992.util.R;

/**
 * Example of an ProgressDialog with Fragment
 */
public class ProgressAlertDialog extends DialogFragment {

  /**
   * Callback interface that the listening activity should implement to receive information
   * from the ProgressDialog. In our case we want to stop the task when the user press
   * back.
   */
  public interface OnProgressDialogListener {

    /**
     * The task was cancelled
     */
    void taskCancelled();

  }

  /*
   * The Listener of the  ProgressDialog about the back key
   */
  private OnKeyListener mOnKeyListener;

  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    // if the Activity implements the listener interface we save its reference
    if (activity instanceof CustomAlertDialog.AlertDialogListener) {
      final OnProgressDialogListener listener = (OnProgressDialogListener) activity;
      mOnKeyListener = new OnKeyListener() {

        @Override
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
          if (keyCode == KeyEvent.KEYCODE_BACK) {
            // The key is back so we return true to tells we intercept it
            // and notify the listener
            listener.taskCancelled();
            dialog.dismiss();
            return true;
          }
          return false;
        }
      };
    }
  }

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    // We create a ProgressDialog
    final ProgressDialog progressDialog = new ProgressDialog(getActivity());
    progressDialog.setMessage(getString(R.string.loading_text));
    progressDialog.setIndeterminate(true);
    progressDialog.setCancelable(false);
    // We DON'T close the Dialog if we touch outside
    progressDialog.setCanceledOnTouchOutside(false);
    // We manage the back button
    progressDialog.setOnKeyListener(mOnKeyListener);
    return progressDialog;
  }


  //USAGE
  /*
  public class MainActivity extends FragmentActivity
        implements ProgressAlertDialog.OnProgressDialogListener {

    // The tag for the Fragment for ProgressDialog into the layout
    private static final String PROGRESS_DIALOG_TAG = "PROGRESS_DIALOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
    }

    // Called when we press on the progressDialog button
    // @param progressDialogButton The button
    public void showProgressDialog(View progressDialogButton) {
      ProgressAlertDialog alertDialog = new ProgressAlertDialog();
      alertDialog.show(getSupportFragmentManager(), PROGRESS_DIALOG_TAG);
    }

    @Override
    public void taskCancelled() {
      Toast.makeText(this, "TASK CANCELLED", Toast.LENGTH_SHORT).show();
    }

  }
   */

}
