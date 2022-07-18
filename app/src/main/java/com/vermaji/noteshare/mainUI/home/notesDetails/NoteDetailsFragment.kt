package com.vermaji.noteshare.mainUI.home.notesDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.vermaji.noteshare.R
import com.vermaji.noteshare.databinding.FragmentNoteDetailsBinding
import com.vermaji.noteshare.mainUI.home.notesDetails.repo.NoteDetailRepoImpl
import com.vermaji.noteshare.mainUI.home.notesDetails.repo.NoteDetailsRepo
import com.vermaji.noteshare.mainUI.viewModels.NoteDetailsVMFactory

class NoteDetailsFragment(noteId:Int) : Fragment() {
    private lateinit var binding: FragmentNoteDetailsBinding
    private lateinit var noteDetailsRepo : NoteDetailsRepo
    private lateinit var viewModel: NoteDetailsViewModel
    private var noteId:Int = noteId

    companion object{
        fun newInstance(noteId: Int): NoteDetailsFragment{
            return NoteDetailsFragment(noteId = noteId)
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note_details,
            container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteDetailsRepo = NoteDetailRepoImpl()
        viewModel = NoteDetailsVMFactory(noteDetailsRepo).create(NoteDetailsViewModel::class.java)
        doNetworkCall()
    }

    private fun doNetworkCall(){
       // viewModel.loadNoteDetails(2)
    }

}