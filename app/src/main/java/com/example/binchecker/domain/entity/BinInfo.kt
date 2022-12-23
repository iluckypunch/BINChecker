package com.example.binchecker.domain.entity

data class BinInfo(
    val scheme: String,
    val type: String,
    val brand: String,
    val prepaid: Boolean,
    val country: Map<String, String>,
    val bank: Map<String, String>,
    var id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}
