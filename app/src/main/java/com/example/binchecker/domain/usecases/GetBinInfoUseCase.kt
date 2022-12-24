package com.example.binchecker.domain.usecases

import com.android.volley.toolbox.StringRequest
import com.example.binchecker.domain.repository.Repository

class GetBinInfoUseCase(private val repository: Repository) {
    fun getBinInfo(cardNumber: String): StringRequest {
        return repository.getBinInfo(cardNumber)
    }
}