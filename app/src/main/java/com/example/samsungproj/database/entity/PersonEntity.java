package com.example.samsungproj.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Person")
public class PersonEntity {

    @ColumnInfo(name="person_id")
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name="name")
    String name;
    @ColumnInfo(name="age")
    String age;

    @Ignore
    public PersonEntity() {

    }

    public PersonEntity(String name, String age) {
        this.name = name;
        this.age = age;
        this.id = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
