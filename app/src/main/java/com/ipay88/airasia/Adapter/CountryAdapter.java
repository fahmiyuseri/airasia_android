package com.ipay88.airasia.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ipay88.airasia.Activity.CountryType;
import com.ipay88.airasia.Country;
import com.ipay88.airasia.Model.BestDealModel;
import com.ipay88.airasia.R;

import java.util.List;

/**
 * Created by IRSB on 14/5/2018.
 */

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder>  {


    private  List<Country> basicValueModelList;
    private  Activity activity;
    private Context context;
    private CountryType countryType;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvValue;

        public MyViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tvName);
        }
    }

    public CountryAdapter(Activity activity, List<Country> basicValueModelList, CountryType countryType) {
        this.activity = activity;
        this.basicValueModelList = basicValueModelList;
        this.countryType = countryType;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.country_layout, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Country basicValueModel = basicValueModelList.get(position);

        holder.tvName.setText(basicValueModel.getCity()+" ("+basicValueModel.getCityCode()+")");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(countryType==CountryType.ORIGIN){
                   TextView tvOrigin =  activity.findViewById(R.id.tvOrigin);
                   if(tvOrigin.getText().length()==0) {
                       tvOrigin.setText(basicValueModel.getCity() + " (" + basicValueModel.getCityCode() + ")");
                       tvOrigin.setTag(basicValueModel);
                   }
                   else{
                       TextView tvDestination =  activity.findViewById(R.id.tvDestination);
                       tvDestination.setText(basicValueModel.getCity()+" ("+basicValueModel.getCityCode()+")");
                       tvDestination.setTag(basicValueModel);


                   }
                }else{
                    TextView tvDestination =  activity.findViewById(R.id.tvDestination);
                    tvDestination.setText(basicValueModel.getCity()+" ("+basicValueModel.getCityCode()+")");
                    tvDestination.setTag(basicValueModel);

                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return basicValueModelList.size();
    }
}
