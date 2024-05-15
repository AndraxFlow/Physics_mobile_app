package com.example.samsungproj.database.entity.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "plans")
public class Plans {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "datetime")
    public String datetime;
    @ColumnInfo(name = "topic_id")
    public int topicId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }





    public Plans(String datetime, int topicId) {
        this.datetime = datetime;
        this.topicId = topicId;
    }
}