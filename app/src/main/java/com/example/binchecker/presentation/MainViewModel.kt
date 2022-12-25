package com.example.binchecker.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.binchecker.data.RepositoryImpl
import com.example.binchecker.domain.entity.BinInfo
import com.example.binchecker.domain.usecases.GetBinInfoUseCase
import com.example.binchecker.domain.usecases.GetSearchHistoryUseCase

class MainViewModel: ViewModel() {

    private val repository = RepositoryImpl

    private val getBinInfoUseCase = GetBinInfoUseCase(repository)
    private val getSearchHistoryUseCase = GetSearchHistoryUseCase(repository)

    val binInfoLD = MutableLiveData<BinInfo>()

    fun getBinInfo(requestResult: String) {
        binInfoLD.value = getBinInfoUseCase.getBinInfo(requestResult)
    }
}