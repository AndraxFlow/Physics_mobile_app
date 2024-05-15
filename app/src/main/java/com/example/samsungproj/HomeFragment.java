package com.example.samsungproj;



import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.samsungproj.database.entity.AppDatabase;
import com.example.samsungproj.database.entity.data.ItemViewModel;
import com.example.samsungproj.database.entity.models.UserInfo;
import com.example.samsungproj.database.entity.data.UserInfoDao;
import com.example.samsungproj.database.entity.data.UserInfoRepository;
import com.example.samsungproj.databinding.FragmentHomeBinding;
import com.example.samsungproj.themes.Kinematika;

import java.util.List;

public class HomeFragment extends Fragment {




    //private AdapterHome adapterHome;
    private ImageView MUTE_btn;    // Кнопка MUTE   (уменьшения громкости до нуля во всем приложении)
    private ImageView UNMUTE_btn;  // Кнопка UNMUTE (увеличения громкости почти до max во всем приложении)


    private List<UserInfo> dataList;

    private Button button1;
    private Button button2;
    private ImageView imageView;


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
        Button button = (Button) view.findViewById(R.id.button1);
        imageView = view.findViewById(R.id.imageView);
        EditText editTextUpdateDb = (EditText) view.findViewById(R.id.editTextUpdateDb);
        EditText editTextId = (EditText) view.findViewById(R.id.editTextId);
        imageView.setImageResource(R.drawable.simple_isaac_newton_apples_trees_green_humor_science_green_background_digital_art_1367075);

        // Кнопка при нажатии, на которую громкость в приложении станет НУЛЕВОЙ (MUTE_btn).
        // Сама кнопка станет невидимой, а ВИДИМОЙ станет кнопка ВКЛючения звука (UNMUTE_btn).

        MUTE_btn = (ImageView)view.findViewById(R.id.stop_sound);

        MUTE_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                AudioManager audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
                MUTE_btn.setVisibility(View.INVISIBLE);
                UNMUTE_btn.setVisibility(View.VISIBLE);
            }
        });

        // Кнопка при нажатии, на которую громкость в приложеии станет почти MAX (UNMUTE_btn).
        // Сама кнопка станет невидимой, а ВИДИМОЙ станет кнопка ВЫКЛючения звука (MUTE_btn).

        UNMUTE_btn = (ImageView)view.findViewById(R.id.play_sound);

        UNMUTE_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                AudioManager audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 90, AudioManager.FLAG_SHOW_UI);
                MUTE_btn.setVisibility(View.VISIBLE);
                UNMUTE_btn.setVisibility(View.INVISIBLE);
            }
        });

        RecyclerView rv_add_station = view.findViewById(R.id.recyclerView);
        rv_add_station.setLayoutManager(new LinearLayoutManager(
                getContext(),
                RecyclerView.VERTICAL,
                false
        ));

        // Установка видимости соответствующих кнопок при запуске Activity
        int app_volume;

        AudioManager audioManager2 = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        app_volume = audioManager2.getStreamVolume(AudioManager.STREAM_MUSIC); // вот тут у меня и была ошибка

        if(app_volume == 0){
            MUTE_btn.setVisibility(View.INVISIBLE);
            UNMUTE_btn.setVisibility(View.VISIBLE);
        }
        else{
            MUTE_btn.setVisibility(View.VISIBLE);
            UNMUTE_btn.setVisibility(View.INVISIBLE);
        }

        AdapterHome allStationAdapter = new AdapterHome(new AdapterHome.StationDiff());
        mStationViewModel.getAllWords().observe(getViewLifecycleOwner(), stationsList -> {
            allStationAdapter.submitList(stationsList);
        });

        rv_add_station.setAdapter(allStationAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


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


    public boolean onKeyUp(int keyCode, KeyEvent event) {

        // при нажатии на кнопку громкости вверх, смена видимости соответствующих кнопок

        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {

            MUTE_btn.setVisibility(View.VISIBLE);
            UNMUTE_btn.setVisibility(View.INVISIBLE);
            return true;

        }

        // при нажатии на кнопку громкости вниз, смена видимости соответствующих кнопок

        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
//  заметьте, тот же код, что и в методе on Create, т.к. необходимо, чтобы фон кнопки менялся только
            // при достижении нулевого уровня громкости

            int app_volume;
            AudioManager audioManager2 = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
            app_volume = audioManager2.getStreamVolume(AudioManager.STREAM_MUSIC);

            if (app_volume == 0) {

                MUTE_btn.setVisibility(View.INVISIBLE);
                UNMUTE_btn.setVisibility(View.VISIBLE);

            } else {

                MUTE_btn.setVisibility(View.VISIBLE);
                UNMUTE_btn.setVisibility(View.INVISIBLE);

            };
            return true;
        }
        return false;
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
