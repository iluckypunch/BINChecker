package com.example.binchecker.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.binchecker.domain.entity.BinInfo
import com.example.binchecker.domain.entity.converters.MapConverter

@Database(entities = [BinInfo::class], version = 1, exportSchema = false)
@TypeConverters(MapConverter::class)
abstract class DatabaseSearchHistory: RoomDatabase() {

    abstract fun daoSearchHistory(): DaoSearchHistory

    companion object {
        // Используется паттерн синглтон для одного экземпляра класса базы данных
        @Volatile
        private var INSTANCE: DatabaseSearchHistory? = null

        fun getDatabase(
            context: Context
        ): DatabaseSearchHistory {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseSearchHistory::class.java,
                    "BinInfoTable"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}