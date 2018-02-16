package com.growlist.caseylemon.growlist_app;

import java.util.List;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Delete;

/**
 * Created by caseylemon on 2/16/18.
 */

@Dao
public interface PlantDao {
    @Query("SELECT * FROM plant")
    List<Plant> getAll();

    @Query("SELECT * FROM user WHERE p_id in (:plantIds)")
    List<Plant> loadAllByIds(int[] plantIds);

    @Query("SELECT * FROM user WHERE name LIKE :name")
    Plant findByName(String name);

    @Insert
    void insertAll(Plant... users);

    @Delete
    void delete(Plant user);
}
