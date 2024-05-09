package com.example.samsungproj.database.entity.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.samsungproj.database.entity.UserInfo;
import com.example.samsungproj.database.entity.UserInfoDao;

import java.util.List;

public class UserInfoRepository {

    private UserInfoDao mStationDAO;
    private LiveData<List<UserInfo>> mAllStations;
    private List<UserInfo> allStations;
    private UserInfoDao stationDAO;

    public UserInfoRepository(Application application) {
        UserInfoDatabase db = UserInfoDatabase.getDatabase(application);
        mStationDAO = db.userInfoDao();
        mAllStations = mStationDAO.getAllUserInfosLiveData();
    }

    public List<UserInfo> getAllStationsList() {
        return allStations;
    }


    public LiveData<List<UserInfo>> getAllStations() {
        return mAllStations;
    }

    public void insert(UserInfo station) {
        UserInfoDatabase.databaseWriteExecutor.execute(() -> {
            mStationDAO.insert(station);

        });
    }



    public void updateStation(int id, String name) {
        UserInfoDatabase.databaseWriteExecutor.execute(() -> {
            mStationDAO.updateName(id, name);
        });
    }



}
