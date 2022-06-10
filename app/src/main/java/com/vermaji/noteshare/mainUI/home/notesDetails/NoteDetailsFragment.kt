package com.vermaji.noteshare.mainUI.home.notesDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.vermaji.noteshare.R
import com.vermaji.noteshare.databinding.FragmentNoteDetailsBinding

class NoteDetailsFragment : Fragment() {
    private lateinit var binding: FragmentNoteDetailsBinding

    companion object{
        fun newInstance(): NoteDetailsFragment{
            return NoteDetailsFragment()
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentNoteDetailsBinding>(inflater, R.layout.fragment_note_details,
            container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}