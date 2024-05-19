package com.example.samsungproj.database.entity.data;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.samsungproj.database.entity.models.UserInfo;

import java.util.List;

public class UserInfoRepository {

    private UserInfoDao mUserInfoDAO;
    private LiveData<List<UserInfo>> listLiveData;
    private List<UserInfo> userInfoList;
    private UserInfoDao userInfoDao;
    private int MaxID;

    public UserInfoRepository(Application application) {
        UserInfoDatabase db = UserInfoDatabase.getDatabase(application);
        mUserInfoDAO = db.userInfoDao();
        listLiveData = mUserInfoDAO.getLastRecordsLiveData();

        listLiveData.observeForever(new Observer<List<UserInfo>>() {
            @Override
            public void onChanged(List<UserInfo> userInfos) {
                userInfoList = userInfos;
            }
        });
    }

    public List<UserInfo> getAllStationsList() {
        return userInfoList;
    }

    public LiveData<List<UserInfo>> getUserInfoList() {
        return listLiveData;
    }

    public void insert(UserInfo station) {
        UserInfoDatabase.databaseWriteExecutor.execute(() -> {
            mUserInfoDAO.insert(station);

        });
    }

    public void update(int id, String name) {
        UserInfoDatabase.databaseWriteExecutor.execute(() -> {
            mUserInfoDAO.updateName(id, name);
        });
    }

    public List<UserInfo> getListUserInfo(){

        UserInfoDatabase.databaseWriteExecutor.execute(() ->{

            userInfoList =  mUserInfoDAO.getLastRecords();
        });
        return userInfoList;

    }

    public int getMaxID(){
        UserInfoDatabase.databaseWriteExecutor.execute(() ->{

            MaxID =  mUserInfoDAO.getMaxTopicId();
        });
        return MaxID;

    }
}
