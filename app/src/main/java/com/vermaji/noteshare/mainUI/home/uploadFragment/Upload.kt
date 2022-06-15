package com.vermaji.noteshare.mainUI.home.uploadFragment

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.chip.Chip
import com.vermaji.noteshare.R
import com.vermaji.noteshare.databinding.FragmentUploadBinding
import com.vermaji.noteshare.loginService.session.SessionManagement
import com.vermaji.noteshare.mainUI.home.uploadFragment.models.CategoriesResponse
import com.vermaji.noteshare.mainUI.home.uploadFragment.models.CategoriesResponseItem
import com.vermaji.noteshare.mainUI.home.uploadFragment.models.NoteInputData
import java.util.concurrent.TimeUnit


class Upload : Fragment() {
    private lateinit var binding: FragmentUploadBinding
    private lateinit var viewModel: UploadViewModel
    var categoryArrayAdapter:ArrayAdapter<String>?=null
    var subCategoryArrayAdapter:ArrayAdapter<String>?=null
    var categories:CategoriesResponse?=null
    var selectedCategory:CategoriesResponseItem?=null
    var fileUri:Uri?=null
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

    @RequiresApi(Build.VERSION_CODES.O)
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
                category.add(item.category)
            }
            val arrayAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_dropdown_item_1line,category)
            binding.idNoteCategory.setAdapter(arrayAdapter)
        })
        binding.idNoteCategory.onItemClickListener =
            OnItemClickListener { parent, arg1, pos, id ->
                binding.idSubCategory.text.clear()
                selectedCategory = categories?.get(pos)
                Toast.makeText(requireContext(),selectedCategory?.category,Toast.LENGTH_SHORT).show()
                val list = selectedCategory?.subcategory!!
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

        binding.idBtnUpload.setOnClickListener {
            val title = binding.idTitleTextField.text.toString()
            val desc = binding.idDescTextField.text.toString()
            val category = binding.idNoteCategory.text.toString()
            val subCategory = binding.idSubCategory.text.toString()
            val language = binding.idLanguage.text.toString()
            if (fileUri!=null){
                if (title.isNotEmpty()&&desc.isNotEmpty()&&category.isNotEmpty()&&subCategory.isNotEmpty()
                    &&language.isNotEmpty()){
                    val userInfo = SessionManagement(requireContext()).getUserInfo()
                    viewModel.uploadFile(fileUri!!, NoteInputData(
                        id = userInfo.id,
                        desc = desc,
                        category = category,
                        subCategory = subCategory,
                        language = language,
                        title = title,
                        uploadTime = System.currentTimeMillis().toString(),
                        tags = arrayListOf("Knowledge", "Study", "common Paper", "Govt Exam"),
                        userId = userInfo.id,
                        userName = userInfo.firstname + userInfo.lastname
                    ))
                }else{
                    Toast.makeText(requireContext(),"One or more fields are empty",Toast.LENGTH_SHORT).show()
                }
            }else
            {
                Toast.makeText(requireContext(),"Select a file or pdf to upload",Toast.LENGTH_SHORT).show()
            }
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
                        fileUri = selectedFileUri
                        Toast.makeText(requireContext(),selectedFileUri.toString(),Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}