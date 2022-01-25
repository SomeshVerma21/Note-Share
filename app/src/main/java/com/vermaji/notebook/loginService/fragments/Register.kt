package com.vermaji.notebook.loginService.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.vermaji.notebook.R
import com.vermaji.notebook.databinding.FragmentRegisterBinding

class Register : Fragment() {
    private lateinit var binding:FragmentRegisterBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_register,container,false)
        binding.idAlreadyHaveAccount.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_register_to_login)
        })
        return binding.root
    }
}