package com.example.samsungproj.database.entity;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AssessmentDao {
    @Insert
    void insert(Assessment assessment);

    @Query("SELECT * FROM assessment")
    List<Assessment> getAllAssessments();
}