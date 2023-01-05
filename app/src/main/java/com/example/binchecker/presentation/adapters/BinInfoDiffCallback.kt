package com.example.binchecker.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.binchecker.domain.entity.BinInfo

class BinInfoDiffCallback: DiffUtil.ItemCallback<BinInfo>() {
    override fun areItemsTheSame(oldItem: BinInfo, newItem: BinInfo): Boolean {
        return oldItem.number == newItem.number
    }

    override fun areContentsTheSame(oldItem: BinInfo, newItem: BinInfo): Boolean {
        return oldItem == newItem
    }
}