package com.github.p4535992.baseandroid01.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.github.p4535992.companymodel.CompanyModel;
import com.github.p4535992.ui.DestinationTextView;
import com.github.p4535992.ui.IndicatorFAB;
import com.github.p4535992.util.view.ViewUtility;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import uk.co.maxcarli.apobus.R;
import uk.co.maxcarli.apobus.util.ViewUtility;
import uk.co.maxcarli.busmodel.BusStop;
import uk.co.maxcarli.ui.BusIndicatorFAB;
import uk.co.maxcarli.ui.DestinationTextView;

/**
 * Placeholder for the BusStopListFragment
 */
public class CompanyModelListFragment extends ListFragment /* Fragment */ {

  /**
   * The number we use to simulate the data
   */
  private static final int TEST_NUM = 100;

  /**
   * The Tag for this Fragment
   */
  public static final String TAG = CompanyModelListFragment.class.getSimpleName();

  /**
   * Interface that the Activity should implement to be notified of a BusStop selection
   */
  public interface OnBusStopSelectedListener {

    /**
     * Invoked when we select a busStop
     *
     * @param companyModelListFragment The selected companyModel
     */
    void onBusStopSelected(CompanyModelListFragment companyModelListFragment);

  }

  /**
   * The Reference of the OnBusStopSelectedListener if any
   */
  private WeakReference<OnBusStopSelectedListener> mOnBusStopSelectedListenerRef;

  /**
   * The Toolbar for this Fragment
   */
  private Toolbar mToolbar;

  /**
   * The ListView
   */
  private ListView mListView;

  /**
   * The Model
   */
  private List<CompanyModel> mModel = new LinkedList<>();

  /**
   * The Model for the SimpleAdapter
   */
  private List<Map<String, Object>> mMapModel = new LinkedList<>();

  /**
   * The current Adapter
   */
  private BaseAdapter mAdapter;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    final View layout = inflater.inflate(R.layout.fragment_company_model_list2, container, false);
    // Get the reference to the Toolbar
    mToolbar = ViewUtility.findViewById(layout, R.id.my_toolbar);
    mToolbar.inflateMenu(R.menu.fragment_company_model_list);
    mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem item) {
        Log.d(TAG, "Map Selected! ");
        return false;
      }
    });
    // The ListView reference
    /*
    mListView = ViewUtility.findViewById(layout, R.id.bus_stop_list);
    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final BusStop selectedBusStop = mModel.get(position);
        if (mOnBusStopSelectedListenerRef != null) {
          final OnBusStopSelectedListener listener = mOnBusStopSelectedListenerRef.get();
          if (listener != null) {
            listener.onBusStopSelected(selectedBusStop);
          }
        }
      }
    });
    */
    //mAdapter = getListAdapter();
    //mListView.setAdapter(mAdapter);
    //getListView().setAdapter(mAdapter);
    return layout;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mAdapter = getCustomAdapter();
    getListView().setAdapter(mAdapter);
  }

  @Override
  public void onListItemClick(ListView l, View v, int position, long id) {
    super.onListItemClick(l, v, position, id);
    final CompanyModel selectedBusStop = mModel.get(position);
    if (mOnBusStopSelectedListenerRef != null) {
      final OnBusStopSelectedListener listener = mOnBusStopSelectedListenerRef.get();
      if (listener != null) {
        listener.onBusStopSelected(selectedBusStop);
      }
    }
  }

  @Override
  public void onStart() {
    super.onStart();
    // We update the model
    createModel();
    createMapModel();
    // We update the ListView
    mAdapter.notifyDataSetChanged();
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnBusStopSelectedListener) {
      final OnBusStopSelectedListener listener = (OnBusStopSelectedListener) context;
      mOnBusStopSelectedListenerRef = new WeakReference<OnBusStopSelectedListener>(listener);
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    if (mOnBusStopSelectedListenerRef != null) {
      mOnBusStopSelectedListenerRef.clear();
      mOnBusStopSelectedListenerRef = null;
    }
  }


  /**
   * @return Simple implementation for the Adapter
   */
  private BaseAdapter getCustomAdapter1() {
    final ArrayAdapter<CompanyModel> adapter =
            new ArrayAdapter<CompanyModel>(getContext(),android.R.layout.simple_list_item_1, mModel);
    return adapter;
  }

  /**
   * @return Adapter that uses custom layout without binding
   */
  private BaseAdapter getCustomAdapter2() {
    final ArrayAdapter<CompanyModel> adapter =
            new ArrayAdapter<CompanyModel>(getContext(),R.layout.simple_company_model_layout, R.id.row_text, mModel);
    return adapter;
  }

  /**
   * @return Adapter that uses custom layout without binding
   */
  private BaseAdapter getCustomAdapter3() {
    final ArrayAdapter<CompanyModel> adapter =
            new ArrayAdapter<CompanyModel>(getContext(),R.layout.company_model_layout, R.id.company_model_name, mModel);
    return adapter;
  }

  /**
   * @return Adapter that maps BusStop field into the row
   */
  private BaseAdapter getCustomAdapter4() {
    final BaseAdapter adapter = new BaseAdapter() {
      @Override
      public int getCount() {
        return mModel.size();
      }

      @Override
      public CompanyModel getItem(int position) {
        return mModel.get(position);
      }

      @Override
      public long getItemId(int position) {
        return position;
      }

      @Override
      public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
          convertView = LayoutInflater.from(getContext()).inflate(R.layout.company_model_layout, null);
        }
        // Current item
        final CompanyModel busStop = getItem(position);
        // Binding
        final IndicatorFAB busIndicatorFAB = ViewUtility.findViewById(convertView, R.id.company_model_indicator);
        final TextView busStopName = ViewUtility.findViewById(convertView, R.id.company_model_name);
        final DestinationTextView destination = ViewUtility.findViewById(convertView, R.id.company_model_destination);
        // We set the data
        String indicator = busStop.name.substring(0, Math.min(busStop.name.length(), 2)).toUpperCase();
        busIndicatorFAB.setText(indicator);
        busStopName.setText(busStop.name);
        destination.setText(busStop.address);
        // We return the item
        return convertView;
      }
    };
    return adapter;
  }

  /**
   * @return Adapter that maps BusStop field into the row using the Holder Pattern
   */
  private BaseAdapter getCustomAdapter5() {
    final BaseAdapter adapter = new BaseAdapter() {

      /**
       * Holder Pattern implementation
       */
      class Holder {
        IndicatorFAB indicatorFAB;
        TextView name;
        DestinationTextView address;
      }

      @Override
      public int getCount() {
        return mModel.size();
      }

      @Override
      public CompanyModel getItem(int position) {
        return mModel.get(position);
      }

      @Override
      public long getItemId(int position) {
        return position;
      }

      @Override
      public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
          convertView = LayoutInflater.from(getContext()).inflate(R.layout.company_model_layout, null);
          holder = new Holder();
          // Binding
          holder.indicatorFAB = ViewUtility.findViewById(convertView, R.id.company_model_indicator);
          holder.name = ViewUtility.findViewById(convertView, R.id.company_model_name);
          holder.address = ViewUtility.findViewById(convertView, R.id.company_model_destination);
          // Save as Tag
          convertView.setTag(holder);
        } else {
          holder = (Holder) convertView.getTag();
        }
        // Current item
        final CompanyModel busStop = getItem(position);
        // We set the data
        String indicator = busStop.name.substring(0, Math.min(busStop.name.length(), 2)).toUpperCase();
        holder.indicatorFAB.setText(indicator);
        holder.name.setText(busStop.name);
        holder.address.setText(busStop.address);
        // We return the item
        return convertView;
      }
    };
    return adapter;
  }

  private static final String[] FROM = {"indicator", "name", "address"};

  private static final int[] TO = {R.id.company_model_indicator, R.id.company_model_name,R.id.company_model_address};

  /**
   * @return Usage of SimpleAdapter
   */
  public BaseAdapter getCustomAdapter6() {
    final BaseAdapter adapter =
            new SimpleAdapter(getContext(), mMapModel,R.layout.company_model_layout, FROM, TO);
    return adapter;
  }

  /**
   * @return Adapter with ViewBinder
   */
  public BaseAdapter getCustomAdapter7() {
    final SimpleAdapter adapter = new SimpleAdapter(getContext(), mMapModel,
                                                           R.layout.bus_stop_layout, FROM, TO);
    adapter.setViewBinder(new SimpleAdapter.ViewBinder() {
      @Override
      public boolean setViewValue(View view, Object data, String textRepresentation) {
        if (view.getId() == R.id.bus_indicator) {
          IndicatorFAB busIndicatorFAB = (IndicatorFAB) view;
          busIndicatorFAB.setText(textRepresentation);
          return true;
        }
        return false;
      }
    });
    return adapter;
  }

  /**
   * @return Adapter for different type of rows
   */
  private BaseAdapter getCustomAdapter() {
    final BaseAdapter adapter = new BaseAdapter() {

      /**
       * The number of type
       */
      private static final int VIEW_TYPE_NUMBER = 2;

      @Override
      public int getViewTypeCount() {
        return VIEW_TYPE_NUMBER;
      }

      @Override
      public int getItemViewType(int position) {
        return position % 2;
      }

      /**
       * Holder Pattern implementation
       */
      class Holder {
        IndicatorFAB indicatorFAB;
        TextView name;
        DestinationTextView address;
      }

      @Override
      public int getCount() {
        return mModel.size();
      }

      @Override
      public CompanyModel getItem(int position) {
        return mModel.get(position);
      }

      @Override
      public long getItemId(int position) {
        return position;
      }

      @Override
      public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
          if (getItemViewType(position) == 0) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.company_model_layout, null);
          } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.company_model_layout2, null);
          }
          holder = new Holder();
          // Binding
          holder.indicatorFAB = ViewUtility.findViewById(convertView, R.id.company_model_indicator);
          holder.name = ViewUtility.findViewById(convertView, R.id.company_model_name);
          holder.address = ViewUtility.findViewById(convertView, R.id.company_model_address);
          // Save as Tag
          convertView.setTag(holder);
        } else {
          holder = (Holder) convertView.getTag();
        }
        // Current item
        final CompanyModel busStop = getItem(position);
        // We set the data
        String indicator = busStop.name.substring(0, Math.min(busStop.name.length(), 2)).toUpperCase();
        holder.indicatorFAB.setText(indicator);
        holder.name.setText(busStop.name);
        holder.address.setText(busStop.address);
        // We return the item
        return convertView;
      }
    };
    return adapter;
  }

  /**
   * Utility method that creates a dummy model
   */
  private void createModel() {
    mModel.clear();
    for (int i = 0; i < TEST_NUM; i++) {
      final CompanyModel item = CompanyModel.Builder.create(i, "Bus Stop #" + i,"Address #" + i,i)
                                   .build();
      mModel.add(item);
    }
  }

  /**
   * Utility method that creates a dummy model for the SimpleAdapter
   */
  private void createMapModel() {
    mMapModel.clear();
    for (int i = 0; i < TEST_NUM; i++) {
      final Map<String, Object> item = new HashMap<>();
      item.put("id", "#" + i);
      item.put("name", "Company Model #" + i);
      //item.put("indicator", "AB");
      item.put("address", "Direction #" + i);
      mMapModel.add(item);
    }
  }
}
