package com.example.binchecker.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.binchecker.domain.entity.BinInfo

@Dao
interface DaoSearchHistory {


    @Query("SELECT * FROM BinInfoTable")
    fun getSearchHistory(): LiveData<List<BinInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBinInfo(binInfo: BinInfo)

    @Delete
    suspend fun deleteBinInfo(binInfo: BinInfo)
}