package com.vermaji.notebook.mainUI.homeFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vermaji.notebook.database.NoteProperty
import com.vermaji.notebook.databinding.HomeItemListBinding
import com.vermaji.notebook.databinding.NoteItemViewBinding

class HomeItemAdapter( private val clickListener: HomeItemListener)
    : RecyclerView.Adapter<HomeItemAdapter.NoteItemViewHolder>() {
    private var homeItems = mutableListOf<NoteProperty>(
        NoteProperty(id = 0, imgSrc = 1,"Hello Note",
        description = "note book description", price = "1001"),
        NoteProperty(id = 0, imgSrc = 1,"Hello Note",
            description = "note book description", price = "1001"),
        NoteProperty(id = 0, imgSrc = 1,"Hello Note",
            description = "note book description", price = "1001"),
        NoteProperty(id = 0, imgSrc = 1,"Hello Note",
            description = "note book description", price = "1001"),
        NoteProperty(id = 0, imgSrc = 1,"Hello Note",
            description = "note book description", price = "1001")
    )
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemViewHolder
    {
        return NoteItemViewHolder.getViewHolder(parent)
    }

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int)
    {
        holder.bind(homeItems[position],clickListener)
    }

    override fun getItemCount(): Int {
        return homeItems.size
    }
    class NoteItemViewHolder private constructor(private val binding: NoteItemViewBinding): RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item: NoteProperty, clickListener: HomeItemListener)
        {
            binding.noteBook = item
            binding.clickListener=clickListener
            binding.executePendingBindings()
        }
        companion object
        {
            fun getViewHolder(parent: ViewGroup): NoteItemViewHolder
            {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = NoteItemViewBinding.inflate(layoutInflater,parent,false)
                return NoteItemViewHolder(view)
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
