package com.example.samsungproj.database.entity.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_info")
public class UserInfo {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "datetime")
    public String datetime;
    @ColumnInfo(name = "topic_id")
    public int topicId;
    @ColumnInfo(name = "scroll_position")
    public int scrollPosition;

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

    public int getScrollPosition() {
        return scrollPosition;
    }



    public void setScrollPosition(int scrollPosition) {
        this.scrollPosition = scrollPosition;
    }

    public UserInfo(String datetime, int topicId, int scrollPosition) {
        this.datetime = datetime;
        this.topicId = topicId;
        this.scrollPosition = scrollPosition;
    }
}