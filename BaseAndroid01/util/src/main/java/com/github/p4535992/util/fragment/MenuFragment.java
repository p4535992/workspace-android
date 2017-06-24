package com.github.p4535992.util.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.github.p4535992.util.R;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * This is a ListFragment with a list of possible styles DialogFragment
 *
 */
public class MenuFragment extends ListFragment {

    /*
     * Encapsulate info of each item
     */
    public static class MenuItem implements Serializable {

        /**
         * UID for Serialization
         */
        private static final long serialVersionUID = -1801876025578318776L;

        public String styleName;
        public String themeName;
        public int theme;
        public int style;
    }

    /**
     * Callback interface that the listening activity should implement to receive
     * selection notification from this Fragment
     */
    public interface StyledDialogItemListener {

        /**
         * Called to notify a selection in the List
         *
         * @param selectedItem The selectedItem
         */
        void itemSelected(MenuItem selectedItem);

    }

    /*
     * The Listener of the  list
     */
    private StyledDialogItemListener mStyledDialogItemListener;

    /**
     * The model of all the possible combination of style/themes with their names
     */
    private List<MenuItem> mModel = new LinkedList<MenuItem>();


    /**
     * Create the MenuFragment with the givem StyledDialogItemListener
     *
     * @param listener The StyledDialogItemListener to get info about selection
     * @return The MenuFragment with the selection list
     */
    public static MenuFragment getMenuFragment(final StyledDialogItemListener listener) {
        MenuFragment fragment = new MenuFragment();
        fragment.setStyledDialogItemListener(listener);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Here the Activity is created so we just need to set the Adapter.
        // There are 4 different types of style and 4 different themes so the
        // different combinations are 16. To do that we just generate all the
        // combination into a model which is a list of Pair objects. It's not
        // efficient but in this case it's not important for our goal. For
        // labels we used string array resources.
        int[] styles = new int[]{DialogFragment.STYLE_NORMAL, DialogFragment.STYLE_NO_TITLE,
                DialogFragment.STYLE_NO_FRAME, DialogFragment.STYLE_NO_INPUT};
        int[] themes = new int[]{android.R.style.Theme_Holo, android.R.style.Theme_Holo_Light_Dialog,
                android.R.style.Theme_Holo_Light, android.R.style.Theme_Holo_Light_Panel};
        String[] styleNames = getResources().getStringArray(R.array.dialog_fragment_styles);
        String[] themeNames = getResources().getStringArray(R.array.dialog_fragment_themes);
        // Create the model
        mModel.clear();
        for (int i = 0; i < styles.length; i++) {
            for (int j = 0; j < themes.length; j++) {
                MenuItem item = new MenuItem();
                item.styleName = styleNames[i];
                item.themeName = themeNames[j];
                item.style = styles[i];
                item.theme = themes[j];
                mModel.add(item);
            }
        }
        // We create the adapter
        final ArrayAdapter<MenuItem> adapter = new ArrayAdapter<MenuItem>(getActivity(),
                android.R.layout.simple_expandable_list_item_1, mModel) {

            class Holder {
                TextView themeView;
                TextView styleView;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Holder holder = null;
                if (convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(getActivity());
                    convertView = inflater.inflate(R.layout.layout_dialog_row, null);
                    holder = new Holder();
                    holder.styleView = (TextView) convertView.findViewById(R.id.style_name);
                    holder.themeView = (TextView) convertView.findViewById(R.id.theme_name);
                    convertView.setTag(holder);
                } else {
                    holder = (Holder) convertView.getTag();
                }
                // Get the value and show the data
                MenuItem item = getItem(position);
                holder.styleView.setText(item.styleName);
                holder.themeView.setText(item.themeName);
                return convertView;
            }

        };
        // Set the adapter
        setListAdapter(adapter);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // We would use this method to get the Activity as StyledDialogItemListener but
        // we decided to pass it as parameter. In this case we could pass a different
        // object as StyledDialogItemListener implementation
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        // Here we just need to call the method on the listener if present
        if (mStyledDialogItemListener != null) {
            // We get the model
            MenuItem item = mModel.get(position);
            mStyledDialogItemListener.itemSelected(item);
        }
    }

    /*
     * Utility method to save the listener
     */
    private void setStyledDialogItemListener(final StyledDialogItemListener listener) {
        this.mStyledDialogItemListener = listener;
    }

}
