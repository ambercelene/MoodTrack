package com.example.moodtrack.dal;

import java.util.Date;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;


@Dao
@TypeConverters(DateHelper.class)
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
     * Observable (LiveData) affects list, later than key given
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
