package com.cinntra.vista.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cinntra.vista.model.DepartMent;

import java.util.List;

@Dao
public interface DepartmentDao {
    @Query("SELECT * FROM table_Department")
    List<DepartMent> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<DepartMent> data);



}
