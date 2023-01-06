package com.example.binchecker.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.binchecker.R
import com.example.binchecker.domain.entity.BinInfo

class SearchHistoryAdapter internal constructor(context: Context): RecyclerView.Adapter<SearchHistoryViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var searchHistoryList = emptyList<BinInfo>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryViewHolder {
        val view = inflater.inflate(R.layout.search_history_item, parent, false)
        return SearchHistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchHistoryViewHolder, position: Int) {
        val binInfo = searchHistoryList[position]
        holder.tvNumber.text = binInfo.number
    }

    internal fun setSearchHistoryList(searchHistoryList: List<BinInfo>) {
        this.searchHistoryList = searchHistoryList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return searchHistoryList.size
    }
}