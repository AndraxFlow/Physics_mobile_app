package com.example.samsungproj.database.entity;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserInfoDao {
    @Insert
    void insert(UserInfo userInfo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUserInfo(UserInfo buttonData);


    @Query("SELECT * FROM user_info")
    List<UserInfo> getAllUserInfos();

    @Update
    void updateButtonData(UserInfo buttonData);

    @Query("SELECT * FROM user_info ORDER BY id DESC LIMIT 1")
    UserInfo getLastButtonData();

    @Query("UPDATE user_info  SET datetime WHERE id= :id")
    void updateName(String id);

}
