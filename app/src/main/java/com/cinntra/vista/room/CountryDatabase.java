package com.cinntra.vista.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.cinntra.vista.model.Countries;

@Database(entities = {Countries.class},version = 1)
public abstract class CountryDatabase extends RoomDatabase {
    public abstract CountryDao myDataDao();
}
