package com.example.dictionaryapp.roomDB

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class MyConverter {
    @TypeConverter
    fun fromEntityList(list: List<EntitySwitch>?): String {
        val gson = Gson()
        val type = object : TypeToken<List<EntitySwitch>?>() {}.type
        return gson.toJson(list, type)
    }
    @TypeConverter
    fun toEntityList(json: String): List<EntitySwitch>? {
        val gson = Gson()
        val type = object : TypeToken<List<EntitySwitch>?>() {}.type
        val list: List<EntitySwitch>?
        list = gson.fromJson(json, type)
        return list
    }
}