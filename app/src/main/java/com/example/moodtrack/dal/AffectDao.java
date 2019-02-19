package com.example.moodtrack.dal;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;

import java.util.Date;
import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
@TypeConverters(DateConverter.class)
public interface AffectDao {

    @Query("SELECT count(*) FROM Affect")
    int getCount();

    @Query("SELECT * FROM Affect")
    LiveData<List<Affect>> getMoodDataList();

    @Query("SELECT * FROM Affect")
    List<Affect> getMoodDataListSync();

    @Insert(onConflict = IGNORE)
    void insert(Affect affect);

    @Update(onConflict = REPLACE)
    void update(Affect affect);

    @Query("DELETE FROM Affect")
    void deleteAll();

    @Query("select * from Affect where id = :id")
    Affect getAffectById(int id);

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
