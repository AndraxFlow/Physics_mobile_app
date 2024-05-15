package com.example.samsungproj.database.entity.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.samsungproj.database.entity.models.Plans;

import java.util.List;

@Dao
public interface PlansDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Plans plans);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPlans(Plans buttonData);


    @Query("SELECT * FROM plans")
    List<Plans> getAllPlans();

    @Query("SELECT * FROM plans")
    LiveData<List<Plans>> getAllPlansLiveData();

    @Update
    void updateButtonData(Plans buttonData);

    @Query("SELECT * FROM plans ORDER BY id DESC LIMIT 1")
    Plans getLastButtonData();

    @Query("UPDATE plans  SET datetime= :name WHERE id= :id")
    void updateName(int id, String name);

    @Query("SELECT MAX(id) AS id, datetime, topic_id " +
            "FROM plans " +
            "GROUP BY datetime " +
            "ORDER BY topic_id DESC")
    List<Plans> getLastRecords();

    @Query("SELECT MAX(id) AS id, datetime, topic_id " +
            "FROM plans " +
            "GROUP BY datetime " +
            "ORDER BY topic_id DESC")
    LiveData<List<Plans>> getLastRecordsLiveData();

    @Query("SELECT topic_id FROM plans ORDER BY topic_id DESC LIMIT 1")
    int getMaxTopicId();

}
