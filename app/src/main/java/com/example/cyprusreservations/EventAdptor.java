package com.example.cyprusreservations;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class EventAdptor extends BaseAdapter {

    private String eventTitle;

    public EventAdptor(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
