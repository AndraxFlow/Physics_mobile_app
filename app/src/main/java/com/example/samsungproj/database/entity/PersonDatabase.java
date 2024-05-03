package com.example.samsungproj.database.entity;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {PersonEntity.class}, version = 1)
public abstract class PersonDatabase extends RoomDatabase {

    public abstract PersonDAO getPersonDAO();

}
