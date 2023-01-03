package com.example.binchecker.domain.repository

import android.content.Context
import com.example.binchecker.domain.entity.BinInfo

interface Repository {
    fun getBinInfo(requestResult: String, cardNumber: String): BinInfo

    fun getSearchHistory(context: Context): List<BinInfo>
}