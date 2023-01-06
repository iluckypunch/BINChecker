package com.example.binchecker.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.binchecker.data.RepositoryImpl
import com.example.binchecker.data.room.DatabaseSearchHistory
import com.example.binchecker.data.room.RepositorySearchHistory
import com.example.binchecker.domain.entity.BinInfo
import com.example.binchecker.domain.usecases.GetBinInfoUseCase
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository = RepositoryImpl

    private val getBinInfoUseCase = GetBinInfoUseCase(repository)

    private val repositorySearchHistory: RepositorySearchHistory

    val searchHistoryList: LiveData<List<BinInfo>>

    private val _binInfoLD = MutableLiveData<BinInfo>()
    val binInfoLD: LiveData<BinInfo>
        get() = _binInfoLD

    private val _errorInput = MutableLiveData<Boolean>()
    val errorInput: LiveData<Boolean>
        get() = _errorInput

    init {
        val daoSearchHistory = DatabaseSearchHistory.getDatabase(application).daoSearchHistory()
        repositorySearchHistory = RepositorySearchHistory(daoSearchHistory)
        searchHistoryList = repositorySearchHistory.searchHistory
    }

    private fun insertInSearchHistory(binInfo: BinInfo) = viewModelScope.launch {
        repositorySearchHistory.insert(binInfo)
    }


    fun getBinInfo(requestResult: String, cardNumber: String) {
        val binInfo = getBinInfoUseCase.getBinInfo(requestResult, cardNumber)
        insertInSearchHistory(binInfo)
        _binInfoLD.value = binInfo
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

    fun resetErrorInput() {
        _errorInput.value = false
    }

    companion object {
        const val CLIENT_ERROR = "com.android.volley.ClientError"
        const val MAX_INPUT_SIZE = 8
    }
}