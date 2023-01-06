@file:Suppress("UNREACHABLE_CODE")

package com.example.binchecker.data

import com.example.binchecker.domain.entity.BinInfo
import com.example.binchecker.domain.repository.Repository
import org.json.JSONObject

object RepositoryImpl: Repository {

    override fun getBinInfo(requestResult: String, cardNumber: String): BinInfo {
        val mainObject = JSONObject(requestResult)
        val countryObject: Map<String, String>? = if (mainObject.getString("country") == "null") {
            null
        } else {
            mainObject.getJSONObject("country").toMap()
        }
        val bankObject: Map<String, String>? = if (mainObject.getString("bank") == "null") {
            null
        } else {
            mainObject.getJSONObject("bank").toMap()
        }
        return BinInfo(
            mainObject.getString("scheme"),
            mainObject.getString("type"),
            mainObject.getString("brand"),
            mainObject.getString("prepaid").toBoolean(),
            countryObject,
            bankObject,
            cardNumber
        )
    }

    private fun JSONObject.toMap(): Map<String, String> {
        val map = mutableMapOf<String, String>()
        val keysItr: Iterator<String> = this.keys()
        while (keysItr.hasNext()) {
            val key = keysItr.next()
            val value = this.get(key).toString()
            map[key] = value
        }
        return map
    }
}