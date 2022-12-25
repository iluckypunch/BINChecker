package com.example.binchecker.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BinInfo(
    val scheme: String? = null,
    val type: String? = null,
    val brand: String? = null,
    val prepaid: Boolean? = null,
    val country: Map<String, String>? = null,
    val bank: Map<String, String>? = null,
    var id: Int = UNDEFINED_ID
): Parcelable {
    companion object {
        const val UNDEFINED_ID = -1
    }
}
