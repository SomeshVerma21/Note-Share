package com.vermaji.noteshare.mainUI.home.fileViewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.FragmentManager
import com.vermaji.noteshare.R
import com.vermaji.noteshare.mainUI.home.fileViewer.fragments.PdfViewFragment

class FileViewerActivity : AppCompatActivity() {
    private lateinit var fragmentManager: FragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_viewer)
        fragmentManager = supportFragmentManager
        setFragment()
    }

    private fun setFragment( ){
        fragmentManager.beginTransaction()
            .add(R.id.fl_file_viewer_fragment,PdfViewFragment.newInstance("",""))
            .commitNow()
    }
}