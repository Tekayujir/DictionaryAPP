package com.example.dictionaryapp.roomDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "isChecked")
data class EntitySwitch
(
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null,
        @ColumnInfo(name = "checked")
        var checked: Boolean = false
)