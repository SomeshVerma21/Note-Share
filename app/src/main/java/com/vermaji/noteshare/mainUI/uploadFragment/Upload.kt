package com.vermaji.noteshare.mainUI.uploadFragment

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.chip.Chip
import com.vermaji.noteshare.R
import com.vermaji.noteshare.databinding.FragmentUploadBinding
import com.vermaji.noteshare.mainUI.uploadFragment.models.CategoriesResponse
import com.vermaji.noteshare.mainUI.uploadFragment.models.CategoriesResponseItem


class Upload : Fragment() {
    private lateinit var binding: FragmentUploadBinding
    private lateinit var viewModel: UploadViewModel
    var categoryArrayAdapter:ArrayAdapter<String>?=null
    var subCategoryArrayAdapter:ArrayAdapter<String>?=null
    var categories:CategoriesResponse?=null
    var selectedCategory:CategoriesResponseItem?=null
    private var uriData:Uri?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[UploadViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_upload,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val languageAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_dropdown_item_1line,
            listOf("HINDI","ENGLISH"))

        binding.idLanguage.setAdapter(languageAdapter)
        binding.idChooseFileBtn.setOnClickListener {
            chooseFile()
        }
        viewModel._categories.observe(viewLifecycleOwner, Observer {
            val category = mutableListOf<String>()
            categories = it
            for (item in it){
                category.add(item.mainCategory)
            }
            val arrayAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_dropdown_item_1line,category)
            binding.idNoteCategory.setAdapter(arrayAdapter)
        })
        binding.idNoteCategory.onItemClickListener =
            OnItemClickListener { parent, arg1, pos, id ->
                binding.idSubCategory.text.clear()
                selectedCategory = categories?.get(pos)
                Toast.makeText(requireContext(),selectedCategory?.mainCategory,Toast.LENGTH_SHORT).show()
                val list = selectedCategory?.subCategories!!
                val adapter = ArrayAdapter(requireContext(),android.R.layout.simple_dropdown_item_1line,list)
                binding.idSubCategory.setAdapter(adapter)
            }
        binding.idNoteTags.setOnEditorActionListener { _, actionId , keyEvent ->
            if (actionId==EditorInfo.IME_ACTION_GO){
                val text = binding.idNoteTags.text
                if (text?.isNotEmpty() == true){
                    addNewTagsChip(text.toString())
                    binding.idNoteTags.text?.clear()
                }
            }
            false
        }
    }

    private fun addNewTagsChip(chipText:String){
        val chip = Chip(requireContext())
        chip.text = chipText
        binding.idNoteTagsChipGroup.addView(chip)
    }

    private fun chooseFile(){
        val pdfIntent = Intent(Intent.ACTION_GET_CONTENT)
        pdfIntent.type  = "application/pdf"
        pdfIntent.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(pdfIntent,101)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode!=RESULT_CANCELED){
            when(requestCode){
                101 -> {
                    if (resultCode==RESULT_OK&&data!=null){
                        val selectedFileUri = data.data
                        Toast.makeText(requireContext(),selectedFileUri.toString(),Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}