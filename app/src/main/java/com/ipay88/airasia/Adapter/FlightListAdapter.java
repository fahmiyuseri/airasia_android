package com.ipay88.airasia.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.ipay88.airasia.Activity.SearchFlightActivity;
import com.ipay88.airasia.Model.FlightModel;
import com.ipay88.airasia.R;

import java.util.Calendar;
import java.util.List;

/**
 * Created by IRSB on 14/5/2018.
 */

public class FlightListAdapter extends RecyclerView.Adapter<FlightListAdapter.MyViewHolder>  {

    public static int selectedItem = -1;

    private  List<FlightModel> basicValueModelList;
    private  Context activity;
    private Context context;
    private SearchFlightActivity.FlightType flightType;
    private Fragment fragment;
    private double totalPrice;
    private Calendar date;
    AdapterViewListener adapterViewListener;
public interface AdapterViewListener{
    void OnSelected(FlightModel flightModel,int pos);
}
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public TextView tvPrice;
        public TextView tvPrice2;
        public TextView tvOrigin;
        public TextView tvDestination;
        public ConstraintLayout frame;

        public MyViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tvName);
            tvPrice = (TextView) view.findViewById(R.id.tvPrice);
            tvPrice2 = (TextView) view.findViewById(R.id.tvPrice2);
            tvOrigin = (TextView) view.findViewById(R.id.tvOrigin);
            tvDestination = (TextView) view.findViewById(R.id.tvDestination);
            frame = (ConstraintLayout) view.findViewById(R.id.frame);

        }
    }

    public FlightListAdapter(Activity activity, List<FlightModel> basicValueModelList, SearchFlightActivity.FlightType flightType) {
        this.activity = activity;
        this.flightType = flightType;
        this.basicValueModelList = basicValueModelList;
       // this.totalPrice = totalPrice;
        selectedItem=-1;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.flight_list_layout, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final FlightModel basicValueModel = basicValueModelList.get(position);
        holder.tvPrice.setText(String.valueOf(basicValueModel.getPrice()));
       // holder.tvPrice2.setText("."+basicValueModel.getPrice().split("\\.")[1]);
        holder.tvOrigin.setText(basicValueModel.getOrigin());
        holder.tvDestination.setText(basicValueModel.getDestination());
        if(position==selectedItem){
            GradientDrawable drawable = (GradientDrawable)holder.frame.getBackground();
            drawable.setStroke(3, Color.RED);
      /*      TextView tvPrice = ((AppCompatActivity) activity).findViewById(R.id.tvPrice);
            TextView tvPrice2 = ((AppCompatActivity) activity).findViewById(R.id.tvPrice2);
            tvPrice.setText(basicValueModel.getPrice().split("\\.")[0]);
            tvPrice2.setText("."+basicValueModel.getPrice().split("\\.")[1]);*/


        }
        else{
            GradientDrawable drawable = (GradientDrawable)holder.frame.getBackground();
            drawable.setStroke(1,  Color.LTGRAY);
        }
        Button btnSubmit = ((AppCompatActivity) activity).findViewById(R.id.btnSubmit);
      /*
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flightType== SearchFlightActivity.FlightType.DEPART) {
                   *//* Intent intent = new Intent(activity, SearchFlightActivity.class);
                    intent.putExtra(Constant.FLIGHTYPE, SearchFlightActivity.FlightType.RETURN);
                    intent.putExtra(Constant.ORIGIN, (Country) v.getTag(Constant.ORIGIN_INT));
                    intent.putExtra(Constant.DESTINATION,(Country) v.getTag(Constant.ORIGIN_INT));
                    intent.putExtra(Constant.DEPARTDATE,(Calendar) v.getTag(Constant.DESTINATION_INT));
                    intent.putExtra(Constant.RETURNDATE,(Calendar) v.getTag(Constant.DEPARTDATE_INT));
                    intent.putExtra(Constant.GUEST,(Guest) v.getTag(Constant.GUEST_INT));
                    activity.startActivity(intent);*//*
                    adapterViewListener.OnSelected(basicValueModelList.get(selectedItem),selectedItem);
                }
                else{
                    Intent intent = new Intent(activity, GuestDetailsActivity.class);
                    intent.putExtra(Constant.FLIGHTYPE, SearchFlightActivity.FlightType.RETURN);
                    activity.startActivity(intent);
                }
            }
        });
*/
        holder.frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(selectedItem!=position){
                selectedItem = position;
//                adapterViewListener.OnSelected(basicValueModel,position);
                    if(flightType==SearchFlightActivity.FlightType.DEPART)
                    btnSubmit.setText("Select Returning flight");
                    else
                        btnSubmit.setText("Guest Details");

                    btnSubmit.setVisibility(View.VISIBLE);
                    adapterViewListener.OnSelected(basicValueModelList.get(selectedItem),selectedItem);

                }
                else {
                    selectedItem=-1;
                    btnSubmit.setVisibility(View.GONE);
                    adapterViewListener.OnSelected(null,selectedItem);

                    //  adapterViewListener.OnSelected(null,-1);

                }

                notifyDataSetChanged();

            }
        });}

    @Override
    public int getItemCount() {
        return basicValueModelList.size();
    }
    public void setAdapterViewListener(AdapterViewListener adapterViewListener){
    this.adapterViewListener = adapterViewListener;
    }
    public void clearSelected(){
    selectedItem=-1;
    notifyDataSetChanged();
    }
}
