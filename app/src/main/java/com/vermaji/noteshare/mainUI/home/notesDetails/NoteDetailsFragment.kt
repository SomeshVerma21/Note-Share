package com.vermaji.noteshare.mainUI.home.notesDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.vermaji.noteshare.R
import com.vermaji.noteshare.databinding.FragmentNoteDetailsBinding
import com.vermaji.noteshare.mainUI.api.RetrofitNoteService
import com.vermaji.noteshare.mainUI.home.notesDetails.models.Data
import com.vermaji.noteshare.mainUI.home.notesDetails.models.NoteDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoteDetailsFragment(noteId:Int) : Fragment() {
    private lateinit var binding: FragmentNoteDetailsBinding
    private var noteId:Int = noteId

    companion object{
        fun newInstance(noteId: Int): NoteDetailsFragment{
            return NoteDetailsFragment(noteId = noteId)
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

    private fun doNetworkCall(){

    }

    private fun loadNoteDetails(){
        val call = RetrofitNoteService.retrofitService.getNoteDetailsById(noteId = noteId)
        call.enqueue(object : Callback<NoteDetails> {
            override fun onResponse(call: Call<NoteDetails>, response: Response<NoteDetails>) {
                if (response.isSuccessful){
                    response.body()?.let { updateNoteData(it) }
                }
            }

            override fun onFailure(call: Call<NoteDetails>, t: Throwable) {

            }
        })
    }

    private fun updateNoteData(noteDetails: NoteDetails){
        val data:Data
        if (noteDetails.data.isNotEmpty()){
            data = noteDetails.data[0]
            binding.tvNoteTitle.text = data.name
            binding.tvNoteAuthor.text = data.userName
            binding.tvAbout.text = data.desc
        }
    }
}