@file:Suppress("UNREACHABLE_CODE")

package com.example.binchecker.data

import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.binchecker.domain.repository.Repository

object RepositoryImpl: Repository {
    private const val START_OF_URL = "https://lookup.binlist.net/"

    override fun getBinInfo(cardNumber: String): StringRequest {
        val url = START_OF_URL + cardNumber
        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                result -> Log.d("RepositoryImpl", "Result: $result")
            },
            {
                error -> Log.d("RepositoryImpl", "Error: $error")
            }
        )
        return request
    }

    override fun getSearchHistory() {
        TODO("Not yet implemented")
    }
}