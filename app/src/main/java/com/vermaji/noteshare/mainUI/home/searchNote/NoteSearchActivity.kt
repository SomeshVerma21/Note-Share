package com.vermaji.noteshare.mainUI.home.searchNote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.vermaji.noteshare.R

class NoteSearchActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_search)
        supportActionBar?.hide()
        toolbar = findViewById(R.id.toolbar)
        initViews()
    }

    private fun initViews(){
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun updateSearchData(){

    }
}