package com.vermaji.noteshare.mainUI.home.notesDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentManager
import com.vermaji.noteshare.R

class NoteDetailsActivity : AppCompatActivity() {
    private lateinit var fragmentManager:FragmentManager
    private var noteId = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_details)
        supportActionBar?.hide()
        fragmentManager = supportFragmentManager
        getIntentValues()
        initViews()
    }

    private fun getIntentValues(){
        if (intent.hasExtra("note_id"))
            noteId = intent.getIntExtra("note_id", 0)
    }

    private fun initViews(){
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        setUpDetailsFg()
    }

    private fun setUpDetailsFg(){
        fragmentManager.beginTransaction()
            .add(R.id.fg_note_details,NoteDetailsFragment.newInstance(noteId = noteId))
            .commitNow()
    }


}