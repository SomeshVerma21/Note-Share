package com.vermaji.notebook.fragments.homeFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vermaji.notebook.database.NoteProperty
import com.vermaji.notebook.databinding.HomeItemListBinding

class HomeItemAdapter(private val list: MutableList<NoteProperty>, private val clickListener: HomeItemListener) : RecyclerView.Adapter<HomeItemAdapter.HomeItemViewHolder>() {
    var homeItems = list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeItemViewHolder
    {
        return HomeItemViewHolder.getViewHolder(parent)
    }

    override fun onBindViewHolder(holder: HomeItemViewHolder, position: Int)
    {
        holder.bind(homeItems[position],clickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    class HomeItemViewHolder private constructor(private val binding: HomeItemListBinding): RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item: NoteProperty, clickListener: HomeItemListener)
        {
            binding.noteBook = item
            binding.clickListener=clickListener
            binding.executePendingBindings()
        }
        companion object
        {
            fun getViewHolder(parent: ViewGroup): HomeItemViewHolder
            {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = HomeItemListBinding.inflate(layoutInflater,parent,false)
                return HomeItemViewHolder(view)
            }
        }
    }
}
class HomeItemListener(val clickListener:(itemId:Long)->Unit)
{
    fun onClick(noteItem: NoteProperty)
    {
        return clickListener(noteItem.id)
    }
}
