package com.mood.tracker.dal;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
@TypeConverters(DateConverter.class)
public interface AffectDao {

    @Query("SELECT * FROM Affect")
    LiveData<List<Affect>> findAllAffects();

    @Query("SELECT * FROM Affect")
    List<Affect> findAllAffectsSync();

    @Insert(onConflict = IGNORE)
    void insertAffect(Affect affect);

    @Update(onConflict = REPLACE)
    void updateAffect(Affect affect);

    @Query("DELETE FROM Affect")
    void deleteAll();

    @Query("select * from Affect where id = :id")
    Affect loadAffectById(int id);

    // ---------------------------------------------------------------------------- //

    /**
     * Observable (LiveData) affects list, later than date given
     *
     * @param after
     * @return
     */
    @Query("SELECT * FROM Affect WHERE Affect.date > :after ")
    LiveData<List<Affect>> findAffectsAfter(Date after);

    /**
     * Not observable, but asynchronous
     *
     * @param after
     * @return
     */
    @Query("SELECT * FROM Affect WHERE Affect.date > :after ")
    List<Affect> findAffectsAfterSync(Date after);

    @Query("SELECT * FROM Affect WHERE Affect.type LIKE :typeName")
    LiveData<List<Affect>> findAffectByType(String typeName);

    @Query("SELECT * FROM Affect WHERE Affect.type LIKE :typeName")
    List<Affect> findAffectsByTypeSync(String typeName);

}
