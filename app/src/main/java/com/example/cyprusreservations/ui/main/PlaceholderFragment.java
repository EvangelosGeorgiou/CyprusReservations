package com.example.cyprusreservations.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cyprusreservations.R;

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



        if(getArguments().getInt(ARG_SECTION_NUMBER) == 1)
        {
            /*

            // CalendarView calendar = root.findViewById(R.id.calendarView2);
            CalendarView calendarView = (CalendarView) root.findViewById(R.id.calendarView);
            EditText guests = root.findViewById(R.id.etGuests);
            RadioGroup group = root.findViewById(R.id.rbGroup);

            int selection = group.getCheckedRadioButtonId();

            long selectedDate = calendarView.getDate(); // get selected date in milliseconds
            String selectedGuests = guests.getText().toString();
            */


        }
        else if(getArguments().getInt(ARG_SECTION_NUMBER) == 2)
        {

             root = inflater.inflate(R.layout.fragment_tab_activity_events, container, false);

        }
        return root;
    }
}