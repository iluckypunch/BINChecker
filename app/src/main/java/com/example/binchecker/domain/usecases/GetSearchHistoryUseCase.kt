package com.example.binchecker.domain.usecases

import com.example.binchecker.domain.repository.Repository

class GetSearchHistoryUseCase(private val repository: Repository) {
    fun getSearchHistory() {
        repository.getSearchHistory()
    }
}