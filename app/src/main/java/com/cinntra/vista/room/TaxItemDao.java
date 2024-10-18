package com.cinntra.vista.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cinntra.vista.model.TaxItem;

import java.util.List;

@Dao
public interface TaxItemDao {
    @Query("SELECT * FROM table_Tax")
    List<TaxItem> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<TaxItem> data);



}
