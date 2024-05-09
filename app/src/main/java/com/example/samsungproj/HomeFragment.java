package com.example.samsungproj;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.samsungproj.database.entity.AppDatabase;
import com.example.samsungproj.database.entity.models.UserInfo;
import com.example.samsungproj.database.entity.data.UserInfoDao;
import com.example.samsungproj.database.entity.data.UserInfoRepository;

import java.util.List;

public class HomeFragment extends Fragment {

    private Button button1;
    private Button button2;

    private Object applicationContext;

    private AppDatabase db;
    private UserInfoDao userInfoDao;
    private UserInfoRepository userInfoRepository;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        button1 = view.findViewById(R.id.button1);
//        button2 = view.findViewById(R.id.button2);
//
//        // Получение экземпляра базы данных
//        db = Room.databaseBuilder(requireContext(), AppDatabase.class, "my-database").build();
//
//        // Получение экземпляра DAO
//        userInfoDao = db.userInfoDao();
//
//        // Выполнение операций с базой данных в фоновом потоке
//        new DatabaseOperationTask().execute();
//    }
//
//    private class DatabaseOperationTask extends AsyncTask<Void, Void, List<UserInfo>> {
//
//        @Override
//        protected List<UserInfo> doInBackground(Void... voids) {
//            // Получение элементов из базы данных
//            return userInfoDao.getAllUserInfos();
//        }
//
//        @Override
//        protected void onPostExecute(List<UserInfo> userInfoList) {
//            super.onPostExecute(userInfoList);
//
//            // Проверка, что в базе данных есть хотя бы два элемента
//            if (userInfoList.size() >= 2) {
//                UserInfo userInfo1 = userInfoList.get(0);
//                UserInfo userInfo2 = userInfoList.get(1);
//
//                // Присвоение значений кнопкам
//                button1.setText(String.valueOf(userInfo1.getId()));
//                button2.setText(String.valueOf(userInfo2.getId()));
//
//            }
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Button editDb = (Button) view.findViewById(R.id.buttonAddDb);
        Button getDb = (Button) view.findViewById(R.id.buttonGetDb);
        EditText editTextUpdateDb = (EditText) view.findViewById(R.id.editTextUpdateDb);
        EditText editTextId = (EditText) view.findViewById(R.id.editTextId);

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
        List<UserInfo> userInfoList =  userInfoRepository.getAllStationsList();

        Log.d("HF", "getUser: " + userInfoList.size());
        for (UserInfo userInfo : userInfoList) {
            Log.d("HF", "getUser: " + userInfo.getId());
        }
        Toast.makeText(getContext(), "Количество записей:" + userInfoList.size(), Toast.LENGTH_SHORT).show();
    }
}
