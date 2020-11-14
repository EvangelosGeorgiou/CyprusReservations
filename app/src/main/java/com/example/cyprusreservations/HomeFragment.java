package com.example.cyprusreservations;

import android.content.Intent;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cyprusreservations.CustomAdaptor;
import com.example.cyprusreservations.MainActivity;
import com.example.cyprusreservations.R;
import com.example.cyprusreservations.StoreInfo;
import com.example.cyprusreservations.ui.login.LoginActivity;
import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class    HomeFragment extends Fragment {

    private ListView listView;
    private String login = "";


    //Iniatialize information about the stores
    String[] titles = {"Pier One","Αγράμπελη","Η Γωνιά", "Finders", "Baraki Live","Confuzio Cafe"};
    String[] description = {"Cafe,RestoBar","Cafe,Bar","Cafe,Bar", "Restaurant,Bar","Bar"," Cafe"};
    int[] logo = {R.drawable.pieronelogo,R.drawable.agrampeli_logo,R.drawable.gonia_logo,R.drawable.finders_grill_bar_logo,R.drawable.barakilive_logo,R.drawable.confuzio_logo};
    float[] rating = {(float)4.2, 5, (float)4.2 , (float) 4.3 , (float) 4.3, (float)4.2};
    int[] phone = {25022375,25344314, 25115315,22750700, 96072277,25738883};
    String[] address = {"Old Port Λεμεσός, 3042","Hatziloizi Mihailidi 12, Λεμεσός 3041", "Hatziloizi Mihailidi , Λεμεσός 3041",
                        "28ης Οκτωβρίου 13, Έγκωμη 2414","Γεώργιου Γρίβα Διγενή, Λευκωσία","Λεωφ. Αρχ. Μακαρίου Γ', Λεμεσός 3021"};
    String[] music = {"Dj","Live","Karaoke","","Live",""};
    String[] football = {"","ΑΕΛ vs ΑΠΟΕΛ","","CHELSE vs LIVERPOOL","","","ΟΜΟΝΟΙΑ vs ΑΠΟΛΛΩΝΑ"};
    String[] openHour = {"09:00","16:00","17:00","16:00","22:00","08:00"};
    String[] closeHour = {"22:30","02:00","02:00","02:00","02:30","23:30"};



    CustomAdaptor customAdaptor;
    private List<StoreInfo> listStoreInfo = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        listView = root.findViewById(R.id.listview);
        setHasOptionsMenu(true);
        //stores all the information for shops in a list
        for(int i=0; i<titles.length; i++){
            StoreInfo storeInfo = new StoreInfo(titles[i],description[i],logo[i],rating[i],phone[i],address[i],
                    music[i],football[i],openHour[i],closeHour[i]);
            listStoreInfo.add(storeInfo);
        }

        customAdaptor = new CustomAdaptor(listStoreInfo,this.getContext());
        listView.setAdapter(customAdaptor);

        return root;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_home_fragment,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        menuItem.setVisible(true);
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

        if(id == R.id.menu_signin){
            Intent in = new Intent(getActivity(),LoginActivity.class);
            startActivity(in);
        }

        if(isLogin().equals("true")){
            MenuItem signin_icon = getActivity().findViewById(R.id.menu_signin);
            signin_icon.setVisible(false);
        }

        return super.onOptionsItemSelected(item);
    }

    public String isLogin(){
        Intent in = getActivity().getIntent();
        login = in.getStringExtra(LoginActivity.LOGIN_STATUS);
        System.out.println("isLogin = "+login);

        if(login == null){
            return login = "";
        }else if(login.equals("true")) {
            return login = "true";
        }
        return login = "";
    }
}
