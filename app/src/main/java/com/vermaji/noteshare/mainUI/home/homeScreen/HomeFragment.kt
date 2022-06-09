package com.vermaji.noteshare.mainUI.home.homeScreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vermaji.noteshare.R
import com.vermaji.noteshare.database.NoteDatabase
import com.vermaji.noteshare.databinding.FragmentHomeBinding
import com.vermaji.noteshare.mainUI.home.HomeItemAdapter
import com.vermaji.noteshare.mainUI.home.HomeItemListener
import com.vermaji.noteshare.mainUI.viewModels.ViewModelFactory

class HomeFragment: Fragment() {
    private lateinit var noteViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noteViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater,
            R.layout.fragment_home,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.idTopViewingRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.idTopViewingRecyclerView.addItemDecoration(GridRecyclerViewDecoration())
        binding.idSecondRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.idSecondRecyclerView.addItemDecoration(GridRecyclerViewDecoration())
        binding.idThirdRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.idThirdRecyclerView.addItemDecoration(GridRecyclerViewDecoration())
        Log.i("home fragment ","view model called ")
        val application = requireNotNull(this.activity).application
        val dataSource =  NoteDatabase.getInstence(application).noteDatabaseDao
        val viewModelFactory = ViewModelFactory(dataSource,application)
        noteViewModel._topDownloads.observe(viewLifecycleOwner, Observer { noteResponse ->
            val mAdapter = HomeItemAdapter(noteResponse, HomeItemListener {
                Toast.makeText(requireContext(),it.toString(),Toast.LENGTH_SHORT).show()
            })
            binding.idTopViewingRecyclerView.adapter = mAdapter
            binding.idSecondRecyclerView.adapter = mAdapter
            binding.idThirdRecyclerView.adapter = mAdapter
        })
        binding.lifecycleOwner = this
    }

}