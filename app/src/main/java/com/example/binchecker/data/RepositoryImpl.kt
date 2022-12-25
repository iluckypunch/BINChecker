@file:Suppress("UNREACHABLE_CODE")

package com.example.binchecker.data

import com.example.binchecker.domain.entity.BinInfo
import com.example.binchecker.domain.repository.Repository
import org.json.JSONObject

object RepositoryImpl: Repository {

    override fun getBinInfo(requestResult: String): BinInfo {
        val mainObject = JSONObject(requestResult)
        return BinInfo(
            mainObject.getString("scheme"),
            mainObject.getString("type"),
            mainObject.getString("brand"),
            mainObject.getString("prepaid").toBoolean(),
            mainObject.getJSONObject("country").toMap(),
            mainObject.getJSONObject("bank").toMap()
        )
    }

    private fun JSONObject.toMap(): Map<String, String>? {
        if (this.toString() == "null") {
            return null
        }
        val map = mutableMapOf<String, String>()
        val keysItr: Iterator<String> = this.keys()
        while (keysItr.hasNext()) {
            val key = keysItr.next()
            val value = this.get(key).toString()
            map[key] = value
        }
        return map
    }

    override fun getSearchHistory() {
        TODO("Not yet implemented")
    }
}