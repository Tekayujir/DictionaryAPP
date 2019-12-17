package com.example.dictionaryapp.roomDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update

@Dao
interface DAOSwitch {

    @Query("SELECT * FROM isChecked")
    fun getChecked(): List<EntitySwitch>

    @Insert
    fun insertChecked(post: EntitySwitch)

    /*@Query("UPDATE isChecked SET checked = :checked WHERE id = 0")
    fun updateChecked(checked: Boolean): EntitySwitch*/
    @Update(onConflict = REPLACE)
    fun updateChecked(entitySwitch: EntitySwitch)
}