package com.example.binchecker.presentation.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.binchecker.R

class SearchHistoryViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val tvNumber = view.findViewById<TextView>(R.id.tv_number)
}