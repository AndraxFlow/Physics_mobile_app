package com.example.samsungproj.database.entity.models;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.samsungproj.database.entity.data.UserInfoRepository;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {

    private UserInfoRepository mRepository;

    private final LiveData<List<UserInfo>> mAllStation;

    public ItemViewModel(Application application) {
        super(application);
        mRepository = new UserInfoRepository(application);
        mAllStation = mRepository.getUserInfoList();
    }

    public LiveData<List<UserInfo>> getAllWords() {
        return mAllStation;
    }

    public void insert(UserInfo station) {
        mRepository.insert(station);
    }
//    public void update(Station station) { mRepository.update(station); }

   // public void updateStations(List<UserInfo> stations) { mRepository.update(stations); } ///

}
