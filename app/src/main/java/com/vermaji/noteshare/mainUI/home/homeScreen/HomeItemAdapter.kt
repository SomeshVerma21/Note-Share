package com.vermaji.noteshare.mainUI.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vermaji.noteshare.databinding.NoteItemViewBinding
import com.vermaji.noteshare.mainUI.home.homeScreen.models.Data
import com.vermaji.noteshare.mainUI.home.homeScreen.models.NoteResponse

class HomeItemAdapter(private val responseData:NoteResponse,private val clickListener: HomeItemListener)
    : RecyclerView.Adapter<HomeItemAdapter.NoteItemViewHolder>() {
    private val list = responseData.list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemViewHolder
    {
        return NoteItemViewHolder.getViewHolder(parent)
    }

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int)
    {
        holder.bind(list[position],clickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    class NoteItemViewHolder private constructor(private val binding: NoteItemViewBinding): RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item: Data, clickListener: HomeItemListener)
        {
            binding.idNoteDownloadCount.text = item.downloads.toString()+" Downloads"
            binding.clickListener = clickListener
            binding.notesData = item
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
    fun onClick(noteItem: Data)
    {
        return clickListener(noteItem.id.toLong())
    }
}
