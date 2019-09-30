package com.ipay88.airasia.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ipay88.airasia.Model.BestDealModel;
import com.ipay88.airasia.Model.RecentSearchModel;
import com.ipay88.airasia.R;

import java.util.List;

/**
 * Created by IRSB on 14/5/2018.
 */

public class BestDealAdapter extends RecyclerView.Adapter<BestDealAdapter.MyViewHolder>  {


    private  List<BestDealModel> basicValueModelList;
    private  Activity activity;
    private Context context;
    private Fragment fragment;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvValue;

        public MyViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tvName);
        }
    }

    public BestDealAdapter(Activity activity, List<BestDealModel> basicValueModelList) {
        this.activity = activity;
        this.basicValueModelList = basicValueModelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bestdeal_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final BestDealModel basicValueModel = basicValueModelList.get(position);

        holder.tvName.setText(basicValueModel.getName());



    }

    @Override
    public int getItemCount() {
        return basicValueModelList.size();
    }
}
