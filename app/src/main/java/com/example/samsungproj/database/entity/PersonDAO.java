package com.example.samsungproj.database.entity;



import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonDAO {

    @Insert
    public void addPerson(PersonEntity person);

    @Update
    public void updatePerson(PersonEntity person);
    @Delete
    public void deletePerson(PersonEntity person);

    @Query("select * from person")
    public List<PersonEntity> getAllPersons();

    @Query("select * from person where person_id ==:person_id")
    public PersonEntity getPerson(int person_id);


}
