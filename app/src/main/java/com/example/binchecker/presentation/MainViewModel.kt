package com.example.binchecker.presentation

import androidx.lifecycle.LiveData
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

    private val _binInfoLD = MutableLiveData<BinInfo>()
    val binInfoLD: LiveData<BinInfo>
        get() = _binInfoLD

    private val _errorInput = MutableLiveData<Boolean>()
    val errorInput: LiveData<Boolean>
        get() = _errorInput

    fun getBinInfo(requestResult: String) {
        _binInfoLD.value = getBinInfoUseCase.getBinInfo(requestResult)
    }

    fun validateInput(input: String): Boolean {
        var result = true
        if (input == CLIENT_ERROR) {
            _errorInput.value = true
            result = false
        }
        if (input.length > MAX_INPUT_SIZE) {
            _errorInput.value = true
            result = false
        }
        return result
    }

    companion object {
        const val CLIENT_ERROR = "com.android.volley.ClientError"
        const val MAX_INPUT_SIZE = 8
    }
}