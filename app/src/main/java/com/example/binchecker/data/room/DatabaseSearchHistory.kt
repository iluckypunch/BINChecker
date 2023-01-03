package com.example.binchecker.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.binchecker.domain.entity.BinInfo
import com.example.binchecker.domain.entity.converters.MapConverter

@Database(entities = [BinInfo::class], version = 1, exportSchema = false)
@TypeConverters(MapConverter::class)
abstract class DatabaseSearchHistory: RoomDatabase() {

    abstract fun daoSearchHistory(): DaoSearchHistory
}