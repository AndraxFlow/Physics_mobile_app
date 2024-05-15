package com.example.samsungproj;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.samsungproj.database.entity.AppDatabase;
import com.example.samsungproj.database.entity.data.ItemViewModel;
import com.example.samsungproj.database.entity.data.UserInfoRepository;
import com.example.samsungproj.database.entity.models.UserInfo;
import com.example.samsungproj.databinding.FragmentHomeBinding;
import com.example.samsungproj.themes.Dinamika;
import com.example.samsungproj.themes.Kinematika;
import com.example.samsungproj.themes.Mkt;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    private RecyclerView recyclerView;
    private Adapter adapter;
    private List<String> dataList;
    private int MaxId;



    private UserInfoRepository userInfoRepository;

    public ListFragment() {
        // Required empty public constructor
    }
    public void setOnItemClickListener(Adapter.OnItemClickListener listener) {
        adapter.setOnItemClickListener(listener);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (userInfoRepository != null) {
            MaxId = userInfoRepository.getMaxID();
        }
    }


//    private class InsertAsyncTask extends AsyncTask<UserInfo, Void, Void> {
//        @Override
//        protected Void doInBackground(UserInfo... userInfo) {
//            // Получение экземпляра базы данных
//            AppDatabase db = Room.databaseBuilder(requireContext(), AppDatabase.class, "my-database").build();
//
//            // Вставка данных в базу данных
//            db.userInfoDao().insertUserInfo(userInfo[0]);
//
//            return null;
//        }
//    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userInfoRepository = new UserInfoRepository(requireActivity().getApplication());

    }
    private void addUser(UserInfo userInfo) { // - добавить условие на добавление или замену
        userInfoRepository.insert(userInfo);
        //userInfoRepository.update(userInfo.getId(), userInfo.getDatetime());
        Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        int[] programImages = {R.drawable.baseline_5g_24,R.drawable.baseline_ac_unit_24,
                R.drawable.baseline_volume_off_24};
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        dataList = new ArrayList<String>();
        // Здесь вы можете добавить элементы в dataList
        dataList.add("Кинематика");
        dataList.add("Динамика");
        dataList.add("Молекулярно кинетическая теория");
        adapter = new Adapter(dataList, programImages);
        recyclerView.setAdapter(adapter);

        final MediaPlayer mediaPlayer1 = MediaPlayer.create(getContext(), R.raw.a1);


        setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                String editTextUpdateDb = dataList.get(position);
                String text = editTextUpdateDb.toString();
                int b = position;
                MaxId++;
                UserInfo userInfo = new UserInfo(text,MaxId ,MaxId);

                Log.d("fsedfsdfsd", "Upd click "+text + " " + MaxId);
                addUser(userInfo);

                Intent intent = new Intent(getActivity(), Themes.class);
                intent.putExtra("ключ", dataList.get(position));

                //String receivedValue = getIntent().getStringExtra("ключ");
                switch (position){
                    case 0:
                        mediaPlayer1.start();
                        intent =  new Intent(getActivity(), Kinematika.class);
                        break;
                    case 1:
                        mediaPlayer1.start();
                        intent =  new Intent(getActivity(), Dinamika.class);
                        break;
                    case 2:
                        mediaPlayer1.start();
                        intent =  new Intent(getActivity(), Mkt.class);
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




