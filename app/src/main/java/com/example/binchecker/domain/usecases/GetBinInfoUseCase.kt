package com.example.binchecker.domain.usecases

import com.example.binchecker.domain.repository.Repository

class GetBinInfoUseCase(private val repository: Repository) {
    fun getBinInfo(cardNumber: String) {
        repository.getBinInfo(cardNumber)
    }
}