package com.example.binchecker.domain.usecases

import android.content.Context
import com.example.binchecker.domain.entity.BinInfo
import com.example.binchecker.domain.repository.Repository

class GetSearchHistoryUseCase(private val repository: Repository) {
    fun getSearchHistory(context: Context): List<BinInfo> {
        return repository.getSearchHistory(context)
    }
}