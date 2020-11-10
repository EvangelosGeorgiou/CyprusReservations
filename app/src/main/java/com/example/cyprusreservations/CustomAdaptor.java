package com.example.cyprusreservations;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.cyprusreservations.ui.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;


public class CustomAdaptor extends BaseAdapter implements Filterable {

    private List<StoreInfo> storeInfoModelList;
    private List<StoreInfo> storeInfoModelListFiltered;
    private Context context;

    public CustomAdaptor(List<StoreInfo> storeInfoModelList, Context context) {
        this.storeInfoModelList = storeInfoModelList;
        this.storeInfoModelListFiltered = storeInfoModelList;
        this.context = context;
    }


    @Override
    public int getCount() {
        return storeInfoModelListFiltered.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(this.context).inflate(R.layout.fragment_home_row,null);

        TextView tvTitle = view.findViewById(R.id.tvTitle);
        TextView tvdesciption = view.findViewById(R.id.tvDescription);
        ImageView image = view.findViewById(R.id.ivLogo);
        RatingBar ratingbar = view.findViewById(R.id.rbRating);

        //setting the information to the xml file
        tvTitle.setText(storeInfoModelListFiltered.get(position).getTitle());
        tvdesciption.setText(storeInfoModelListFiltered.get(position).getDescription());
        image.setImageResource(storeInfoModelListFiltered.get(position).getLogo());
        ratingbar.setRating(storeInfoModelListFiltered.get(position).getRating());

        // onClick to the cardview to visit the stores
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sending store information to the next page
                Intent in = new Intent(context, LoginActivity.class);
                in.putExtra("storeInfo",storeInfoModelListFiltered.get(position));
                context.startActivity(in);
            }
        });

        return view;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
System.out.println("Inside the getFilter");
                if(charSequence == null || charSequence.length() ==0){
                    filterResults.count = storeInfoModelList.size();
                    filterResults.values = storeInfoModelList;
                    System.out.println("charSequence is null");
                }else{
                    System.out.println("charSequence is not null. inside the else");
                    String searchStr = charSequence.toString().toLowerCase();
                    List<StoreInfo> resultData = new ArrayList<>();

                    for(StoreInfo storeInfo: storeInfoModelList){
                        if(storeInfo.getTitle().toLowerCase().contains(searchStr)){
                            resultData.add(storeInfo);
                        }
                        filterResults.count = resultData.size();
                        filterResults.values = resultData;
                    }
                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                storeInfoModelListFiltered = (List<StoreInfo>)filterResults.values;
                notifyDataSetChanged();;
            }
        };
        return filter;
    }
}

