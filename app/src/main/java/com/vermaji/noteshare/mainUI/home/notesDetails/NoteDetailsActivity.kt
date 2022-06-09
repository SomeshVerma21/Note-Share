package com.vermaji.noteshare.mainUI.home.notesDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.vermaji.noteshare.R

class NoteDetailsActivity : AppCompatActivity() {
    private lateinit var fragmentManager:FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_details)
        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .add(R.id.fg_note_details,NoteDetailsFragment.newInstance())
            .commitNow()
    }
}