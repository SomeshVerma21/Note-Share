package com.vermaji.noteshare.mainUI.home.homeScreen.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vermaji.noteshare.R
import com.vermaji.noteshare.mainUI.home.homeScreen.models.Data
import com.vermaji.noteshare.mainUI.home.homeScreen.models.NoteResponse
import com.vermaji.noteshare.mainUI.home.notesDetails.NoteDetailsActivity
import java.util.*
import kotlin.random.Random

class HomeItemAdapter(val context:Context, private val responseData:NoteResponse,private val clickListener: HomeItemListener)
    : RecyclerView.Adapter<HomeItemAdapter.NoteItemViewHolder>() {
    private val list = responseData.data
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item_,parent,false)
        return NoteItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int)
    {
        val noteResponse = list[position]
        holder.noteTitle.text = noteResponse.name
        holder.tv_note_rating.text = Random.nextInt(2, 5).toString()
        holder.itemView.setOnClickListener {
            val intent = Intent(context, NoteDetailsActivity::class.java).also {
                it.putExtra("note_id", noteResponse.id)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
    class NoteItemViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView)
    {
        val noteTitle = itemView.findViewById<TextView>(R.id.tv_note_title)
        val tv_note_rating = itemView.findViewById<TextView>(R.id.tv_note_rating)
    }
}
class HomeItemListener(val clickListener:(itemId:Long)->Unit)
{
    fun onClick(noteItem: Data)
    {
        return clickListener(noteItem.id.toLong())
    }
}
