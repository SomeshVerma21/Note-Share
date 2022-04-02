package com.vermaji.noteshare.mainUI.homeFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vermaji.noteshare.R
import com.vermaji.noteshare.database.NoteDatabase
import com.vermaji.noteshare.databinding.FragmentHomeBinding
import com.vermaji.noteshare.mainUI.viewModels.NoteViewModel
import com.vermaji.noteshare.mainUI.viewModels.ViewModelFactory

class HomeFragment: Fragment() {
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var binding: FragmentHomeBinding

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
        noteViewModel = ViewModelProvider(this,viewModelFactory)[NoteViewModel::class.java]
        noteViewModel.checkData()

        val mAdapter = HomeItemAdapter(HomeItemListener {  })
        binding.idTopViewingRecyclerView.adapter = mAdapter
        binding.idSecondRecyclerView.adapter = mAdapter
        binding.idThirdRecyclerView.adapter = mAdapter
        binding.lifecycleOwner = this
    }

}