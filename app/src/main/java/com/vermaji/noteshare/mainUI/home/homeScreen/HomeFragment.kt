package com.vermaji.noteshare.mainUI.home.homeScreen

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vermaji.noteshare.R
import com.vermaji.noteshare.databinding.FragmentHomeBinding
import com.vermaji.noteshare.mainUI.api.RetrofitNoteService
import com.vermaji.noteshare.mainUI.home.homeScreen.adapter.HomeItemAdapter
import com.vermaji.noteshare.mainUI.home.homeScreen.adapter.HomeItemListener
import com.vermaji.noteshare.mainUI.home.homeScreen.models.NoteResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment: Fragment(){
    private lateinit var noteViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noteViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_home,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.idTopViewingRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.idTopViewingRecyclerView.addItemDecoration(GridRecyclerViewDecoration())
        binding.rvSectionTow.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.rvSectionTow.addItemDecoration(GridRecyclerViewDecoration())
        binding.rvSectionThree.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.rvSectionThree.addItemDecoration(GridRecyclerViewDecoration())
        binding.lifecycleOwner = this
        doNetworkCall()
    }

    private fun doNetworkCall(){
        loadTopDownloads()
        loadDataSectionTwo()
        loadDataSectionThree()
    }

    private fun updateSectionData(noteResponse: NoteResponse , sectionIndex: Int){
        val mAdapter = HomeItemAdapter(requireContext(), noteResponse, HomeItemListener {
            Toast.makeText(requireContext(),it.toString(),Toast.LENGTH_SHORT).show()
        })
        Handler().postDelayed(Runnable { hideShimmerLayout(sectionIndex) },2000)
        when(sectionIndex){
                0 ->{
                    binding.idTopViewingRecyclerView.adapter = mAdapter
                }
                1 -> {
                    binding.rvSectionTow.adapter = mAdapter
                }
                2 -> {
                    binding.rvSectionThree.adapter = mAdapter
                }
            else -> {}
        }
    }

    private fun hideShimmerLayout(sectionIndex: Int){
        when(sectionIndex){
            0 -> {
                binding.shimmerSectionOne.visibility = View.GONE
                binding.fLSectionOne.visibility = View.VISIBLE
            }
            1 ->{
                binding.shimmerSectionTow.visibility = View.GONE
                binding.fLSectionTwo.visibility = View.VISIBLE
            }
            2 -> {
                binding.shimmerSectionThree.visibility = View.GONE
                binding.fLSectionThree.visibility = View.VISIBLE
            }
            else -> {}
        }
    }

    private fun loadTopDownloads(){
        val call = RetrofitNoteService.retrofitService.topDownloads()
        call.enqueue(object : Callback<NoteResponse>{
            override fun onResponse(call: Call<NoteResponse>, response: Response<NoteResponse>) {
                if (response.isSuccessful)
                    response.body()?.let { updateSectionData(noteResponse = it , 0) }
            }

            override fun onFailure(call: Call<NoteResponse>, t: Throwable) {
                binding.fLSectionOne.visibility = View.GONE
            }

        })
    }

    private fun loadDataSectionTwo(){
        val call = RetrofitNoteService.retrofitService.topDownloads()
        call.enqueue(object : Callback<NoteResponse>{
            override fun onResponse(call: Call<NoteResponse>, response: Response<NoteResponse>) {
                if (response.isSuccessful)
                    response.body()?.let { updateSectionData(noteResponse = it , 1) }
            }

            override fun onFailure(call: Call<NoteResponse>, t: Throwable) {
                binding.clParentSection.visibility = View.GONE
            }

        })
    }

    private fun loadDataSectionThree(){
        val call = RetrofitNoteService.retrofitService.topDownloads()
        call.enqueue(object : Callback<NoteResponse>{
            override fun onResponse(call: Call<NoteResponse>, response: Response<NoteResponse>) {
                if (response.isSuccessful)
                    response.body()?.let { updateSectionData(noteResponse = it, 2) }
            }

            override fun onFailure(call: Call<NoteResponse>, t: Throwable) {
                binding.clParentSection.visibility = View.GONE
            }

        })
    }

}