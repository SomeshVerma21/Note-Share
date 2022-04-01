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
import com.vermaji.notebook.databinding.FragmentRegisterBinding
import com.vermaji.notebook.loginService.apiHelper.RetrofitAuthApi
import com.vermaji.notebook.loginService.models.NewUser
import com.vermaji.notebook.loginService.models.Response
import retrofit2.Call
import retrofit2.Callback

class Register : Fragment() {
    private lateinit var binding:FragmentRegisterBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_register,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "New User"
        binding.idAlreadyHaveAccount.setOnClickListener(View.OnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.idLoginFragmentNavigation,Login.newInstance())
                ?.commitNow()
        })

        binding.idBtnSignUp.setOnClickListener {
            val fullName = binding.idRegFullName.text.toString()
            val email = binding.idRegEmail.text.toString()
            val password = binding.idRegPassword.text.toString()

            if (fullName.isNotEmpty()&&email.isNotEmpty()&&password.isNotEmpty()){
                val nameGroup = fullName.split(" ")
                if (nameGroup.size>1){
                    val user = NewUser(0,nameGroup[0],nameGroup[1], email, password,"false")
                    postCreateUser(requireContext(),user)
                }else
                {
                    val user = NewUser(0,nameGroup[0],"", email, password,"false")
                    postCreateUser(requireContext(),user)
                }
            }
        }
    }

    private fun postCreateUser(context:Context,user:NewUser){
        val call = RetrofitAuthApi.retrofitService.registerUser(user)

        call.enqueue(object :Callback<Response>{
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.body()?.status.equals("success")){
                    Toast.makeText(context,response.body()?.data?.email,Toast.LENGTH_SHORT).show()
                }
                else
                    Toast.makeText(context,response.message(),Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Toast.makeText(context,t.message,Toast.LENGTH_SHORT).show()
            }

        })
    }


    companion object{
        fun newInstance() = Register()
    }
}