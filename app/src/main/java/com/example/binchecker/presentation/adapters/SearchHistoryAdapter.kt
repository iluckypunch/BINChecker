package com.example.binchecker.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.binchecker.R
import com.example.binchecker.domain.entity.BinInfo

class SearchHistoryAdapter internal constructor(context: Context): RecyclerView.Adapter<SearchHistoryViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var searchHistoryList = emptyList<BinInfo>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryViewHolder {
        val layout = when(viewType){
            VIEW_TYPE -> R.layout.search_history_item
            else -> throw RuntimeException("Unknown viewType $viewType")
        }
        val view = inflater.inflate(layout, parent, false)
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

    companion object {
        const val VIEW_TYPE = 1

        const val MAX_POOL_SIZE = 30
    }

    override fun getItemCount(): Int {
        return searchHistoryList.size
    }
}