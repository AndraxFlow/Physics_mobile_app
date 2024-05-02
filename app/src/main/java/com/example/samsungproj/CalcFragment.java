package com.example.samsungproj;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samsungproj.Adapter;
import com.example.samsungproj.FormulesCalc.FArch;
import com.example.samsungproj.FormulesCalc.SorX;
import com.example.samsungproj.R;
import com.example.samsungproj.themes.Dinamika;
import com.example.samsungproj.themes.Kinematika;

import java.util.ArrayList;
import java.util.List;

public class CalcFragment extends Fragment {

    private RecyclerView recyclerView;
    private Adapter adapter;
    private List<String> dataList;

    public CalcFragment() {
        // Required empty public constructor
    }
    public void setOnItemClickListener(Adapter.OnItemClickListener listener) {
        adapter.setOnItemClickListener(listener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        dataList = new ArrayList<String>();
        // Здесь вы можете добавить элементы в dataList
        dataList.add("СИЛА АРХИМЕДА");
        dataList.add("РАССТОЯНИЕ И ПОЛОЖЕНИЕ ТЕЛА ");
        adapter = new Adapter(dataList);
        recyclerView.setAdapter(adapter);
        setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Действия при нажатии на элемент списка
                // Например, открытие экрана редактирования челленджа

                Intent intent;// = new Intent(getActivity(), Themes.class);
                //intent.putExtra("ключ", dataList.get(position));

                //String receivedValue = getIntent().getStringExtra("ключ");
                switch (position){
                    case 0:
                        intent =  new Intent(getActivity(), FArch.class);
                        break;
                    case 1:
                        intent =  new Intent(getActivity(), SorX.class);
                        break;
                    default:
                        intent =  new Intent(getActivity(), Dinamika.class);
                        break;
                }


                startActivity(intent);

            }
        });
        return view;
    }
}

