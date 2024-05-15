package com.example.samsungproj.database.entity.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.samsungproj.database.entity.models.Plans;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Plans.class}, version= 3, exportSchema = false)
public abstract class PlansDatabase extends RoomDatabase {
    public abstract PlansDao PlansDao();
    private static volatile PlansDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static PlansDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PlansDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    PlansDatabase.class, "plans")
                            .createFromAsset("database/my-database.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
