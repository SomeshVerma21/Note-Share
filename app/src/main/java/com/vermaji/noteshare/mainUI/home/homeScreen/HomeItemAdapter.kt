package com.vermaji.noteshare.mainUI.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vermaji.noteshare.R
import com.vermaji.noteshare.databinding.NoteItemViewBinding
import com.vermaji.noteshare.mainUI.home.homeScreen.models.Data
import com.vermaji.noteshare.mainUI.home.homeScreen.models.NoteResponse
import com.vermaji.noteshare.mainUI.home.notesDetails.NoteDetailsActivity

class HomeItemAdapter(val context:Context, private val responseData:NoteResponse,private val clickListener: HomeItemListener)
    : RecyclerView.Adapter<HomeItemAdapter.NoteItemViewHolder>() {
    private val list = responseData.list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item_,parent,false)
        return NoteItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int)
    {
        holder.itemView.setOnClickListener {
            val intent = Intent(context,NoteDetailsActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
    class NoteItemViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView)
    {

    }
}
class HomeItemListener(val clickListener:(itemId:Long)->Unit)
{
    fun onClick(noteItem: Data)
    {
        return clickListener(noteItem.id.toLong())
    }
}
