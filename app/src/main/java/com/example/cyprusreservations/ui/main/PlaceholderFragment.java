package com.example.cyprusreservations.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cyprusreservations.MyReservationsActivity;
import com.example.cyprusreservations.R;
import com.example.cyprusreservations.ReservationActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_reservation, container, false);
       // TextView tv = root.findViewById(R.id.section_label);


        if(getArguments().getInt(ARG_SECTION_NUMBER) == 1)
        {
           // CalendarView calendar = root.findViewById(R.id.calendarView2);

            /*
            CalendarView calendar = (CalendarView) root.findViewById(R.id.cvReservation);
            TextView textView = root.findViewById(R.id.tvMyReservations);


            calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                    String date = String.valueOf(dayOfMonth)+"/"+String.valueOf(month)+"/"+String.valueOf(year);

                    textView.setText(date);
                }
            });

*/
        }
        else if(getArguments().getInt(ARG_SECTION_NUMBER) == 2)
        {
            root = inflater.inflate(R.layout.fragment_tab_activity_events, container, false);
            TextView date_events= root.findViewById(R.id.tvEvents);
            CalendarView calendar = root.findViewById(R.id.cvReservation);
            TextView events = root.findViewById(R.id.tvEvents2);
        }
        return root;
    }

}