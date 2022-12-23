package com.example.binchecker.domain.repository

import com.android.volley.toolbox.StringRequest

interface Repository {
    fun getBinInfo(cardNumber: String): StringRequest

    fun getSearchHistory()
}