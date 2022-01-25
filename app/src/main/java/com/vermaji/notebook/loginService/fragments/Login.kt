package com.vermaji.notebook.loginService.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.vermaji.notebook.R
import com.vermaji.notebook.databinding.FragmentLoginBinding

class Login : Fragment() {
    private lateinit var binding:FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false)
        binding.idCreateNewAccount.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_login_to_register)
        })
        return binding.root
    }
}