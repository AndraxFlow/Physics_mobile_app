package com.example.samsungproj.database.entity.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.samsungproj.database.entity.models.UserInfo;

import java.util.List;

@Dao
public interface UserInfoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(UserInfo userInfo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUserInfo(UserInfo buttonData);


    @Query("SELECT * FROM user_info")
    List<UserInfo> getAllUserInfos();

    @Query("SELECT * FROM user_info")
    LiveData<List<UserInfo>> getAllUserInfosLiveData();

    @Update
    void updateButtonData(UserInfo buttonData);

    @Query("SELECT * FROM user_info ORDER BY id DESC LIMIT 1")
    UserInfo getLastButtonData();

    @Query("UPDATE user_info  SET datetime= :name WHERE id= :id")
    void updateName(int id, String name);


    //@Query("UPDATE user_info  SET id= :id WHERE datetime= :name")

//    @Query("SELECT * FROM user_info WHERE datetime IN (SELECT MAX(datetime) FROM user_info GROUP BY datetime) ORDER BY id DESC")
//    List<UserInfo> getLastRecords();

    @Query("SELECT MAX(id) AS id, datetime, topic_id, scroll_position " +
                  "FROM user_info " +
                  "GROUP BY datetime " +
                  "ORDER BY topic_id DESC")
    List<UserInfo> getLastRecords();

    @Query("SELECT MAX(id) AS id, datetime, topic_id, scroll_position " +
            "FROM user_info " +
            "GROUP BY datetime " +
            "ORDER BY topic_id DESC")
    LiveData<List<UserInfo>> getLastRecordsLiveData();

    @Query("SELECT topic_id FROM user_info ORDER BY topic_id DESC LIMIT 1")
    int getMaxTopicId();

}
