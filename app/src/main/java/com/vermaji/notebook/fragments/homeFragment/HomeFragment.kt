package com.vermaji.notebook.fragments.homeFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vermaji.notebook.R
import com.vermaji.notebook.database.NoteDatabase
import com.vermaji.notebook.databinding.FragmentHomeBinding
import com.vermaji.notebook.viewModels.NoteViewModel
import com.vermaji.notebook.viewModels.ViewModelFactory

class HomeFragment: Fragment() {
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater,
            R.layout.fragment_home,container,false)
        binding.homeItemRecyclerView.layoutManager = LinearLayoutManager(context)
        Log.i("home fragment ","view model called ")
        val application = requireNotNull(this.activity).application
        val dataSource =  NoteDatabase.getInstence(application).noteDatabaseDao
        val viewModelFactory = ViewModelFactory(dataSource,application)
        noteViewModel = ViewModelProvider(this,viewModelFactory).get(NoteViewModel::class.java)
        noteViewModel.checkData()

        val mAdapter = noteViewModel.list?.let {
            HomeItemAdapter(it, HomeItemListener { noteID ->
                Toast.makeText(context, "$noteID", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_homeFragment_to_details)
            })
        }
        binding.homeItemRecyclerView.adapter = mAdapter

        if (mAdapter != null) {
            if(mAdapter.itemCount<1)
               binding.idPlaceholderText.visibility = View.VISIBLE
            else
                binding.idPlaceholderText.visibility = View.INVISIBLE
        }
        binding.lifecycleOwner = this
        return binding.root
    }

}