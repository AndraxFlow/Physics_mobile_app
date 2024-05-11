package com.example.samsungproj;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.samsungproj.database.entity.AppDatabase;
import com.example.samsungproj.database.entity.data.ItemViewModel;
import com.example.samsungproj.database.entity.models.UserInfo;
import com.example.samsungproj.database.entity.data.UserInfoDao;
import com.example.samsungproj.database.entity.data.UserInfoRepository;
import com.example.samsungproj.databinding.FragmentHomeBinding;

import java.util.List;

public class HomeFragment extends Fragment {




    //private AdapterHome adapterHome;


    private List<UserInfo> dataList;

    private Button button1;
    private Button button2;


    private Object applicationContext;

    private AppDatabase db;
    private UserInfoDao userInfoDao;
    private UserInfoRepository userInfoRepository;


    public FragmentHomeBinding binding;
    private ItemViewModel mStationViewModel;

    public ItemViewModel getStationViewModel() {
        return mStationViewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        mStationViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
    }
    public HomeFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Button editDb = (Button) view.findViewById(R.id.buttonAddDb);
        Button getDb = (Button) view.findViewById(R.id.buttonGetDb);
        EditText editTextUpdateDb = (EditText) view.findViewById(R.id.editTextUpdateDb);
        EditText editTextId = (EditText) view.findViewById(R.id.editTextId);

        RecyclerView rv_add_station = view.findViewById(R.id.recyclerView);
        rv_add_station.setLayoutManager(new LinearLayoutManager(
                getContext(),
                RecyclerView.VERTICAL,
                false
        ));

        AdapterHome allStationAdapter = new AdapterHome(new AdapterHome.StationDiff());
        mStationViewModel.getAllWords().observe(getViewLifecycleOwner(), stationsList -> {
            allStationAdapter.submitList(stationsList);
        });

        rv_add_station.setAdapter(allStationAdapter);



        // Здесь вы можете добавить элементы в dataList


        editDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = editTextUpdateDb.getText().toString();
                int b = Integer.parseInt(editTextId.getText().toString());
                UserInfo userInfo = new UserInfo(a, b,b);
                Log.d("HF", "Size: " + userInfo.getId());

                addUser(userInfo);


            }
        });

        getDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUser();
            }
        });


        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userInfoRepository = new UserInfoRepository(requireActivity().getApplication());

    }


    private void addUser(UserInfo userInfo) {
        userInfoRepository.insert(userInfo);
        Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
    }

    private void updateUser(UserInfo userInfo) {
        userInfoRepository.update(userInfo.getId(), userInfo.getDatetime());
        Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
    }

    private void getUser() {
        List<UserInfo> userInfoList =  userInfoRepository.getListUserInfo();

        Log.d("HF", "getUser: " + userInfoList.size());
        for (UserInfo userInfo : userInfoList) {
            Log.d("HF", "getUser: " + userInfo.getId());
        }
        Toast.makeText(getContext(), "Количество записей:" + userInfoList.size(), Toast.LENGTH_SHORT).show();
    }
}
