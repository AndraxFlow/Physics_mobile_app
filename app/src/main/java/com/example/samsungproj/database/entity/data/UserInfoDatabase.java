package com.example.samsungproj.database.entity.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.samsungproj.database.entity.models.UserInfo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {UserInfo.class}, version= 3, exportSchema = false)
public abstract class UserInfoDatabase extends RoomDatabase {
    public abstract UserInfoDao userInfoDao();
    private static volatile UserInfoDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static UserInfoDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (UserInfoDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    UserInfoDatabase.class, "user_info")
                            .createFromAsset("database/my-database.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
