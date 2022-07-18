package com.vermaji.noteshare.mainUI.home.searchNote

import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.gestures.Orientation
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vermaji.noteshare.R

class NoteSearchActivity : AppCompatActivity(){
    private lateinit var toolbar: Toolbar
    private lateinit var etSearchNote: EditText
    private lateinit var rvNoteSearchList:RecyclerView
    private lateinit var adapter: SearchItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_search)
        supportActionBar?.hide()
        toolbar = findViewById(R.id.toolbar)
        initViews()
    }

    private fun initViews(){
        rvNoteSearchList = findViewById(R.id.rv_note_search_list)
        etSearchNote = findViewById(R.id.et_search_note)
        etSearchNote.doOnTextChanged { text, start, before, count ->
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        }
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        adapter = SearchItemAdapter()
        rvNoteSearchList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvNoteSearchList.adapter = adapter
    }

    private fun searchForNote(text:String){

    }
}