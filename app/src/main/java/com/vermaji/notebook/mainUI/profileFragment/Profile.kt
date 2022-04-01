package com.vermaji.notebook.mainUI.profileFragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.vermaji.notebook.loginService.LoginActivity
import com.vermaji.notebook.R
import com.vermaji.notebook.databinding.FragmentProfileBinding

class Profile : Fragment() {
    private lateinit var binding:FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile,container,false)
        binding.idLoginLogout.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        })
        return binding.root
    }

}