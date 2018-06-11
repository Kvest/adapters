package com.kvest.adapters.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface TimeDao {
    @Query("SELECT * FROM time ORDER BY id DESC")
    fun listenTimeEntities(): LiveData<List<TimeEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTimeEntity(entity: TimeEntity)

    @Query("DELETE FROM time WHERE id = :id")
    fun deleteById(id: Long)
}