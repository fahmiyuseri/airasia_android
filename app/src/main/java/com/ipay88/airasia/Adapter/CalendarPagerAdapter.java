package com.ipay88.airasia.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ipay88.airasia.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CalendarPagerAdapter extends PagerAdapter {
    Context context;
    Calendar calendar;
    SimpleDateFormat df3 = new SimpleDateFormat("EEE dd MMM", Locale.getDefault());


    private List<String> listdate = new ArrayList<>();

    public CalendarPagerAdapter(Context context,List<String> listdate){

        this.context=context;
        this.listdate=listdate;
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
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.flightlist_calendar_layout,
                container, false);
        TextView strdate = layout.findViewById(R.id.strDate);
        strdate.setText(listdate.get(position));

        container.addView(layout,0);
        LinearLayout.LayoutParams layoutParam1 =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
/*
        LinearLayout lFrame = new LinearLayout(context);
      //  lFrame.removeAllViews();
        lFrame.setLayoutParams(layoutParam1);
        //lFrame.setVerticalGravity(Gravity.CENTER_VERTICAL);
        lFrame.setOrientation(LinearLayout.VERTICAL);
        ImageView imageView = new ImageView(context);
        int padding = context.getResources().getDimensionPixelSize(R.dimen.padding_medium);
      *//*  imageView.setPadding(padding, padding, padding, padding);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setImageResource(GalImages[position]);*//*
        //((ViewPager) container).addView(imageView, 0);

        TextView textView = new TextView(context);
        textView.setPadding(padding,padding,padding,padding);
        textView.setText(listdate.get(position));
        textView.setGravity(Gravity.CENTER);

        TextView textView2 = new TextView(context);
        textView2.setPadding(padding,padding,padding,padding);
        textView2.setText("MYR84");
        textView2.setGravity(Gravity.CENTER);
        lFrame.addView(textView);
        lFrame.addView(textView2);*/
       // ((ViewPager) container).addView(layout, 0);

        return layout;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public float getPageWidth(int position) {
        return 0.33f;
    }

    public void setListdate(List<String> listdate){
        this.listdate = listdate;
        notifyDataSetChanged();
    }
}