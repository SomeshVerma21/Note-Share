package com.vermaji.notebook.mainUI.uploadFragment

import android.content.ActivityNotFoundException
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.vermaji.notebook.R
import com.vermaji.notebook.database.NoteDatabase
import com.vermaji.notebook.database.NoteProperty
import com.vermaji.notebook.databinding.FragmentUploadBinding
import com.vermaji.notebook.mainUI.viewModels.NoteViewModel
import com.vermaji.notebook.mainUI.viewModels.ViewModelFactory

class Upload : Fragment() {
    private lateinit var binding:FragmentUploadBinding
    private var uriData:Uri?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_upload,container,false)
        binding.idUploadBtn.setOnClickListener(View.OnClickListener {
            if (binding.idUploadTitle.text.isNotEmpty()&&binding.idUploadDescription.text.isNotEmpty()
                    &&binding.idUploadPrice.text.isNotEmpty())
            {
                val application = requireNotNull(this.activity).application
                val dataSource =  NoteDatabase.getInstence(application).noteDatabaseDao
                val viewModelFactory = ViewModelFactory(dataSource,application)
                val noteViewModel = ViewModelProvider(this,viewModelFactory).get(NoteViewModel::class.java)
                noteViewModel.insert(NoteProperty(
                        title = binding.idUploadTitle.text.toString(),
                        description = binding.idUploadDescription.text.toString(),
                        price = "$ "+binding.idUploadPrice.text.toString(),
                        imgSrc = R.drawable.ic_pdf_note
                ))
                Toast.makeText(activity,"Note uploaded",Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.upload)
            }else
            {
                //val file = File(uriData?.path)
                Toast.makeText(context, "Enter files details",Toast.LENGTH_SHORT).show()
            }
        })
        binding.idUploadThumbnail.setOnClickListener(View.OnClickListener {
            launchFileIntent()
        })
        return binding.root
    }
    private fun launchFileIntent()
    {
        try {
            fileLauncher.launch("application/pdf")
        }catch (e:ActivityNotFoundException)
        {
            e.printStackTrace()
        }
    }
    private var fileLauncher = registerForActivityResult(ActivityResultContracts.GetContent()){
        uri:Uri? ->
        if (uri!=null)
            uriData = uri
    }
}