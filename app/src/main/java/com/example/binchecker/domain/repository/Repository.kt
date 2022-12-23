package com.example.binchecker.domain.repository

interface Repository {
    fun getBinInfo(cardNumber: String)

    fun getSearchHistory()
}