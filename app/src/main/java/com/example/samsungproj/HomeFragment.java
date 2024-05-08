package com.example.samsungproj;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.samsungproj.database.entity.AppDatabase;
import com.example.samsungproj.database.entity.UserInfo;
import com.example.samsungproj.database.entity.UserInfoDao;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Button button1;
    private Button button2;

    private Object applicationContext;

    private AppDatabase db;
    private UserInfoDao userInfoDao;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button1 = view.findViewById(R.id.button1);
        button2 = view.findViewById(R.id.button2);

        // Получение экземпляра базы данных
        db = Room.databaseBuilder(requireContext(), AppDatabase.class, "my-database").build();

        // Получение экземпляра DAO
        userInfoDao = db.userInfoDao();

        // Выполнение операций с базой данных в фоновом потоке
        new DatabaseOperationTask().execute();
    }

    private class DatabaseOperationTask extends AsyncTask<Void, Void, List<UserInfo>> {

        @Override
        protected List<UserInfo> doInBackground(Void... voids) {
            // Получение элементов из базы данных
            return userInfoDao.getAllUserInfos();
        }

        @Override
        protected void onPostExecute(List<UserInfo> userInfoList) {
            super.onPostExecute(userInfoList);

            // Проверка, что в базе данных есть хотя бы два элемента
            if (userInfoList.size() >= 2) {
                UserInfo userInfo1 = userInfoList.get(0);
                UserInfo userInfo2 = userInfoList.get(1);

                // Присвоение значений кнопкам
                button1.setText(String.valueOf(userInfo1.getId()));
                button2.setText(String.valueOf(userInfo2.getId()));

            }
        }
    }
}
