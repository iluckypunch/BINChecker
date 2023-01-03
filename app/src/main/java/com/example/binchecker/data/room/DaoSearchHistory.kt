package com.example.binchecker.data.room

import androidx.room.*
import com.example.binchecker.domain.entity.BinInfo

@Dao
interface DaoSearchHistory {


    @Query("SELECT * FROM BinInfoTable")
    fun getSearchHistory(): List<BinInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBinInfo(binInfo: BinInfo)

    @Delete
    fun deleteBinInfo(binInfo: BinInfo)
}