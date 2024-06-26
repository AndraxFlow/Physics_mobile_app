package com.example.samsungproj;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samsungproj.FormulesCalc.A;
import com.example.samsungproj.FormulesCalc.FArch;
import com.example.samsungproj.FormulesCalc.SorX;
import com.example.samsungproj.themes.Dinamika;

import java.util.ArrayList;
import java.util.List;

public class CalcFragment extends Fragment {

    private RecyclerView recyclerView;
    private AdapterClass adapter;
    private List<String> dataList;

    int[] programImages = {R.drawable.icons8___48, R.drawable.icons8__64, R.drawable.icons8_representation_of_gravity_falls_over_incline_plane_48};

    String[] names = {"СИЛА АРХИМЕДА","РАССТОЯНИЕ И ПОЛОЖЕНИЕ ТЕЛА","УСКОРЕНИЕ"};
    public CalcFragment() {
    }
    public void setOnItemClickListener(AdapterClass.OnItemClickListener listener) {
        adapter.setOnItemClickListener(listener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calc, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        final MediaPlayer mediaPlayer1 = MediaPlayer.create(getContext(), R.raw.a2);
        dataList = new ArrayList<String>();
        // Здесь вы можете добавить элементы в dataList
        dataList.add("СИЛА АРХИМЕДА");
        dataList.add("РАССТОЯНИЕ И ПОЛОЖЕНИЕ ТЕЛА");
        dataList.add("УСКОРЕНИЕ");
        adapter = new AdapterClass(dataList, getActivity(), programImages, names);
        recyclerView.setAdapter(adapter);
        setOnItemClickListener(new AdapterClass.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), Themes.class);
                intent.putExtra("ключ", dataList.get(position));

                switch (position){
                    case 0:
                        mediaPlayer1.start();
                        intent =  new Intent(getActivity(), FArch.class);
                        break;
                    case 1:
                        mediaPlayer1.start();
                        intent =  new Intent(getActivity(), SorX.class);
                        break;
                    case 2:
                        mediaPlayer1.start();
                        intent =  new Intent(getActivity(), A.class);
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

