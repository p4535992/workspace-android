package com.github.p4535992.util.fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.p4535992.util.Const;
import com.github.p4535992.util.R;

/**
 * This is a Dialog that uses a specific style and theme with a custom data inside
 */
public class StyledDialogFragment extends DialogFragment {

  /*
   * The Key for the MenuItem in the arguments
   */
  private static final String MENU_ITEM_KEY = Const.PKG + ".fragment.MENU_ITEM_KEY";

  /**
   * Create a StyledDialogFragment with the given
   *
   * @param menuItem The item selected
   * @return The StyledDialogFragment instance
   */
  public static StyledDialogFragment getStyledDialogFragment(MenuFragment.MenuItem menuItem) {
    StyledDialogFragment fragment = new StyledDialogFragment();
    // We create the arguments
    Bundle args = new Bundle();
    args.putSerializable(MENU_ITEM_KEY, menuItem);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // We set the style and theme
    MenuFragment.MenuItem menuItem = (MenuFragment.MenuItem) getArguments().getSerializable(MENU_ITEM_KEY);
    setStyle(menuItem.style, menuItem.theme);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // We use the same layout of the row in the list
    View dialogLayout = inflater.inflate(R.layout.layout_dialog, container, false);
    // We show the related values
    TextView styleView = (TextView) dialogLayout.findViewById(R.id.style_name);
    TextView themeView = (TextView) dialogLayout.findViewById(R.id.theme_name);
    // Set the values
    MenuFragment.MenuItem menuItem = (MenuFragment.MenuItem) getArguments().getSerializable(MENU_ITEM_KEY);
    styleView.setText(menuItem.styleName);
    themeView.setText(menuItem.themeName);
    // Manage the Button
    dialogLayout.findViewById(R.id.close_button).setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        // We have to dismiss the Dialog
        dismiss();
      }
    });
    // Return the created layout
    return dialogLayout;
  }

  /*
  public class MainActivity extends FragmentActivity implements MenuFragment.StyledDialogItemListener {

    //The Tag for the Log

    private static final String TAG_LOG = MainActivity.class.getName();

    //The Fragment with the different options

    private static MenuFragment mMenuFragment;


    //The tag for the Fragment into the layout

    private static final String MENU_FRAGMENT_TAG = "MENU_FRAGMENT";


    //The tag for the DialogFragment into the layout

    private static final String DIALOG_FRAGMENT_TAG = "DIALOG_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      // If the fragment is not into the state we create and insert into
      // the main layout
      if (savedInstanceState == null) {
        mMenuFragment = MenuFragment.getMenuFragment(this);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mMenuFragment).commit();
        Log.d(TAG_LOG, MENU_FRAGMENT_TAG + " created and added!");
      } else {
        Log.d(TAG_LOG, MENU_FRAGMENT_TAG + " already present!");
      }
    }

    @Override
    public void itemSelected(final MenuFragment.MenuItem menuItem) {
      Log.d(TAG_LOG, "Style " + menuItem.styleName + " and theme " + menuItem.themeName + " selected!");
      // We create a Fragment Transaction
      final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
      // And check if a DialogFragment is already present
      Fragment previousFragment = getSupportFragmentManager().findFragmentByTag(DIALOG_FRAGMENT_TAG);
      if (previousFragment != null) {
        // If it is we remove it into the same transaction
        fragmentTransaction.remove(previousFragment);
      }
      // Uncomment the next instruction to test what happen with the back key and
      // transaction into the Back Stack
      // fragmentTransaction.addToBackStack(null);
      // Create and show the dialog.
      DialogFragment newDialogFragment = StyledDialogFragment.getStyledDialogFragment(menuItem);
      // BE carefull that this show also commit the transaction
      newDialogFragment.show(fragmentTransaction, DIALOG_FRAGMENT_TAG);
    }

  }
   */

}
