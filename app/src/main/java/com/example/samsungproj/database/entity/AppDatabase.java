package com.example.samsungproj.database.entity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Assessment.class, UserInfo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AssessmentDao assessmentDao();
    public abstract UserInfoDao userInfoDao();
}