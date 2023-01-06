package com.example.binchecker.data.room

import androidx.lifecycle.LiveData
import com.example.binchecker.domain.entity.BinInfo

class RepositorySearchHistory(private val daoSearchHistory: DaoSearchHistory) {

    val searchHistory: LiveData<List<BinInfo>> = daoSearchHistory.getSearchHistory()

    suspend fun insert(binInfo: BinInfo) {
        daoSearchHistory.insertBinInfo(binInfo)
    }

    suspend fun delete(binInfo: BinInfo) {
        daoSearchHistory.deleteBinInfo(binInfo)
    }
}