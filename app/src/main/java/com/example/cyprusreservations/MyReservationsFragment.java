package com.example.cyprusreservations;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class MyReservationsFragment extends Fragment {

    private ListView listView;









    ReservationsAdaptor reservationsAdaptor;
    private List<StoreInfo> listStoreInfo = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_myreservations, container, false);
        //final TextView textView = root.findViewById(R.id.text_slideshow);






        return root;
    }
}