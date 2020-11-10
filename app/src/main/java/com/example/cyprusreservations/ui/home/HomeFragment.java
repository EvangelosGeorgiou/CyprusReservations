package com.example.cyprusreservations.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cyprusreservations.CustomAdaptor;
import com.example.cyprusreservations.R;
import com.example.cyprusreservations.StoreInfo;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ListView listView;

    String[] titles = {"Piero One","Oreos Trellos","Confusio", "Pikantiko", "O Fanos","Lamna Rota"};
    String[] description = {"cafe,","cafe","bar", "bar","restaubar"," restaubar"};
    int[] logo = {R.drawable.pieronelogo,R.drawable.pierone_image,R.drawable.pieronelogo,R.drawable.pieronelogo,R.drawable.pierone_image,R.drawable.pieronelogo};
    float[] rating = {1,2,3,4,5,1};


    CustomAdaptor customAdaptor;
    private List<StoreInfo> listStoreInfo = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        listView = root.findViewById(R.id.listview);

        //stores all the information for shops in a list
        for(int i=0; i<titles.length; i++){
            StoreInfo storeInfo = new StoreInfo(titles[i],description[i],logo[i],rating[i]);
            listStoreInfo.add(storeInfo);
        }

        customAdaptor = new CustomAdaptor(listStoreInfo,this.getContext());
        listView.setAdapter(customAdaptor);

        return root;
    }
    //TODO search 

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                System.out.println(s);
                customAdaptor.getFilter().filter(s);
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.app_bar_search)
            return true;

        return super.onOptionsItemSelected(item);
    }
}
