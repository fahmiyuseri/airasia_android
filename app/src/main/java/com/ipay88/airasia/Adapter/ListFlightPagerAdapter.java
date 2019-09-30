package com.ipay88.airasia.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ipay88.airasia.Activity.SearchFlightActivity;
import com.ipay88.airasia.Model.FlightModel;
import com.ipay88.airasia.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ListFlightPagerAdapter extends PagerAdapter implements  FlightListAdapter.AdapterViewListener
{
    Activity context;
    Calendar calendar;
    SearchFlightActivity.FlightType flightType;
    ViewGroup layout;
    String origin;
    String dest;
    List<FlightModel> listFlight = new ArrayList<>();
    FlightListAdapter.AdapterViewListener adapterViewListener;

    private int[] GalImages = new int[] {
            R.drawable.bestdealpic,
            R.drawable.bestdealpic,
            R.drawable.bestdealpic,
            R.drawable.bestdealpic,
            R.drawable.bestdealpic,
            R.drawable.bestdealpic,
            R.drawable.bestdealpic,
            R.drawable.bestdealpic,
            R.drawable.bestdealpic,
            R.drawable.bestdealpic,
            R.drawable.bestdealpic,
            R.drawable.bestdealpic,
            R.drawable.bestdealpic

    };
    private List<String> listdate = new ArrayList<>();

    public ListFlightPagerAdapter(Activity context, List<String> listdate, SearchFlightActivity.FlightType flightType,String origin,String destination){

        this.context=context;
        this.listdate=listdate;
        this.flightType=flightType;
        this.origin=origin;
        this.dest=destination;
    }
    @Override
    public int getCount() {
        return listdate.size();
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((View) object);
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        Log.d("sada","execute "+listdate.get(position));
        listFlight.clear();
        if(flightType== SearchFlightActivity.FlightType.DEPART) {
            listFlight.add(new FlightModel(origin, dest, 30.00));
            listFlight.add(new FlightModel(origin, dest, 100.00));
            listFlight.add(new FlightModel(origin, dest, 100.00));
            listFlight.add(new FlightModel(origin, dest, 100.00));
            listFlight.add(new FlightModel(origin, dest, 120.00));
            listFlight.add(new FlightModel(origin, dest, 980.00));
        }else{
            listFlight.add(new FlightModel(dest, origin, 30.00));
            listFlight.add(new FlightModel(dest, origin,  100.00));
            listFlight.add(new FlightModel(dest, origin,  560.00));
            listFlight.add(new FlightModel(dest, origin,  230.00));
            listFlight.add(new FlightModel(dest, origin, 120.00));
            listFlight.add(new FlightModel(dest, origin, 980.00));
        }
        LayoutInflater inflater = LayoutInflater.from(context);
        layout = (ViewGroup) inflater.inflate(R.layout.fragment_search_flight,
                container, false);
        TextView tv = layout.findViewById(R.id.section_label);
        tv.setText(listdate.get(position));
        container.addView(layout,0);

        LinearLayoutManager mLayoutManagerDate = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        RecyclerView rvFlightList = layout.findViewById(R.id.rvFlightList);
        rvFlightList.setLayoutManager(mLayoutManagerDate);
        FlightListAdapter flightAdapter = new FlightListAdapter(context,listFlight,flightType);
        flightAdapter.setAdapterViewListener(this);
        rvFlightList.setAdapter(flightAdapter);
        return layout;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public float getPageWidth(int position) {
        return 1;
    }

    public void setListdate(List<String> listdate){
        this.listdate = listdate;
        notifyDataSetChanged();
    }
    public void setListener(FlightListAdapter.AdapterViewListener adapterViewListener){
        this.adapterViewListener = adapterViewListener;
    }

    @Override
    public void OnSelected(FlightModel flightModel, int pos) {
        adapterViewListener.OnSelected(flightModel,pos);
    }
}