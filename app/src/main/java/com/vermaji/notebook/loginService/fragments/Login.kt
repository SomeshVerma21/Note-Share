package com.vermaji.notebook.loginService.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.vermaji.notebook.R
import com.vermaji.notebook.databinding.FragmentLoginBinding
import com.vermaji.notebook.loginService.apiHelper.RetrofitAuthApi
import com.vermaji.notebook.loginService.models.LoginUser
import com.vermaji.notebook.loginService.models.Response
import com.vermaji.notebook.loginService.session.SessionManagement
import retrofit2.Call
import retrofit2.Callback

class Login : Fragment() {
    private lateinit var binding:FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "Login User"
        binding.idCreateNewAccount.setOnClickListener(View.OnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.idLoginFragmentNavigation,Register.newInstance())
                ?.commitNow()
        })

        binding.idBtnSignIn.setOnClickListener {
            val email = binding.idLoginEmail.text.toString()
            val password = binding.idLoginPassword.text.toString()
            if (email.isNotEmpty()&&password.isNotEmpty()){
                val user = LoginUser(email = email, password = password)
                postLoginUser(requireContext(),user)
            }
        }
    }

    private fun postLoginUser(context:Context,user:LoginUser){
        val call = RetrofitAuthApi.retrofitService.loginUser(user)
        call.enqueue(object :Callback<Response>{
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.body()?.status.equals("success")){
                    Toast.makeText(context,response.body()?.message,Toast.LENGTH_SHORT).show()
                    val sessionManagement = SessionManagement(context)
                    response.body()?.data?.let { sessionManagement.saveUserInfo(it) }
                }else{
                    Toast.makeText(context,response.body()?.message,Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Toast.makeText(context,t.message,Toast.LENGTH_SHORT).show()
            }

        })
    }

    companion object{
        fun newInstance() = Login()
    }
}