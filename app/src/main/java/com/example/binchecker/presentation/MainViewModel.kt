package com.example.binchecker.presentation

import androidx.lifecycle.ViewModel
import com.android.volley.RequestQueue
import com.example.binchecker.data.RepositoryImpl
import com.example.binchecker.domain.usecases.GetBinInfoUseCase
import com.example.binchecker.domain.usecases.GetSearchHistoryUseCase

class MainViewModel: ViewModel() {

    private val repository = RepositoryImpl

    private val getBinInfoUseCase = GetBinInfoUseCase(repository)
    private val getSearchHistoryUseCase = GetSearchHistoryUseCase(repository)

    fun getBinInfo(cardNumber: String, queue: RequestQueue) {
         queue.add(getBinInfoUseCase.getBinInfo(cardNumber))
    }

    private fun parseJSON() {
        TODO()
    }


}