package com.example.binchecker.domain.repository

import com.example.binchecker.domain.entity.BinInfo

interface Repository {
    fun getBinInfo(requestResult: String): BinInfo

    fun getSearchHistory()
}