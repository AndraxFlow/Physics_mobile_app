package com.example.samsungproj.database.entity.data;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.samsungproj.database.entity.models.UserInfo;

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


}
