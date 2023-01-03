package com.example.binchecker.domain.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "BinInfoTable")
data class BinInfo(
    val scheme: String? = null,
    val type: String? = null,
    val brand: String? = null,
    val prepaid: Boolean? = null,
    val country: Map<String, String>? = null,
    val bank: Map<String, String>? = null,
    @PrimaryKey val number: String = UNDEFINED_ID
): Parcelable {
    companion object {
        const val UNDEFINED_ID = ""
    }
}
