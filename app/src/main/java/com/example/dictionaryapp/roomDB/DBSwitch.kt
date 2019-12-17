package com.example.dictionaryapp.roomDB

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [EntitySwitch::class], version = 1)
@TypeConverters(MyConverter::class)
abstract class DBSwitch : RoomDatabase() {
    abstract fun daoSwitch() : DAOSwitch
}