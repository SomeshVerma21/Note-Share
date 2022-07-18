package com.vermaji.noteshare.mainUI.home.searchNote

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vermaji.noteshare.R

class SearchItemAdapter: RecyclerView.Adapter<SearchItemAdapter.SearchItemViewHolder>() {
    val list = listOf<String>("","","","","","")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return SearchItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class SearchItemViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){

    }
}