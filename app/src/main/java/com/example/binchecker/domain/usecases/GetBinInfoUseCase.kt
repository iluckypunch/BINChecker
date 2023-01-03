package com.example.binchecker.domain.usecases

import com.example.binchecker.domain.entity.BinInfo
import com.example.binchecker.domain.repository.Repository

class GetBinInfoUseCase(private val repository: Repository) {
    fun getBinInfo(requestResult: String, cardNumber: String): BinInfo {
        return repository.getBinInfo(requestResult, cardNumber)
    }
}