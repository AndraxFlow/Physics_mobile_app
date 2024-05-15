package com.example.samsungproj.database.entity.data;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.samsungproj.database.entity.models.Plans;

import java.util.List;

public class PlansRepository {

    private PlansDao mPlansDAO;
    private LiveData<List<Plans>> listLiveData;
    private List<Plans> PlansList;
    private PlansDao PlansDao;
    private int MaxID;

    public PlansRepository(Application application) {
        PlansDatabase db = PlansDatabase.getDatabase(application);
        mPlansDAO = db.PlansDao();
        listLiveData = mPlansDAO.getLastRecordsLiveData();

        listLiveData.observeForever(new Observer<List<Plans>>() {
            @Override
            public void onChanged(List<Plans> Plans) {
                PlansList = Plans;
            }
        });
    }

    public List<Plans> getAllStationsList() {
        return PlansList;
    }


    public LiveData<List<Plans>> getPlansList() {
        return listLiveData;
    }

    public void insert(Plans station) {
        PlansDatabase.databaseWriteExecutor.execute(() -> {
            mPlansDAO.insert(station);

        });
    }



    public void update(int id, String name) {
        PlansDatabase.databaseWriteExecutor.execute(() -> {
            mPlansDAO.updateName(id, name);
        });
    }

    public List<Plans> getListPlans(){

        PlansDatabase.databaseWriteExecutor.execute(() ->{

            PlansList =  mPlansDAO.getLastRecords();
        });
        return PlansList;

    }

    public int getMaxID(){
        PlansDatabase.databaseWriteExecutor.execute(() ->{

            MaxID =  mPlansDAO.getMaxTopicId();
        });
        return MaxID;

    }



}
