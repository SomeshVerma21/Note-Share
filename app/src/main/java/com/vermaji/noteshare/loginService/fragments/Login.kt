package com.vermaji.noteshare.loginService.fragments

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.vermaji.noteshare.R
import com.vermaji.noteshare.databinding.FragmentLoginBinding
import com.vermaji.noteshare.loginService.apiHelper.RetrofitAuthApi
import com.vermaji.noteshare.loginService.models.LoginUser
import com.vermaji.noteshare.loginService.models.Response
import com.vermaji.noteshare.loginService.session.SessionManagement
import com.vermaji.noteshare.mainUI.home.MainActivity
import com.vermaji.noteshare.network.Connectivity
import retrofit2.Call
import retrofit2.Callback

class Login : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var progressDialog:ProgressDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "Login User"
        progressDialog = ProgressDialog(requireContext())
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
                if (Connectivity.checkInternetConnection(requireContext()))
                {
                    showDialog()
                    postLoginUser(requireContext(),user)
                }
                else{
                    val snackBar = Snackbar.make(it,"No internet connection",Snackbar.LENGTH_SHORT)
                    snackBar.show()
                }
            }
        }
    }

    private fun postLoginUser(context:Context,user:LoginUser){
        val call = RetrofitAuthApi.retrofitService.loginUser(user)
        call.enqueue(object :Callback<Response>{
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                progressDialog.cancel()
                if (response.body()?.status.equals("success")){
                    Toast.makeText(context,response.body()?.message,Toast.LENGTH_SHORT).show()
                    val sessionManagement = SessionManagement(context)
                    response.body()?.data?.let {
                        sessionManagement.saveUserInfo(it).also {
                            gotoHomeScreen()
                        }
                    }
                }else{
                    Toast.makeText(context,response.body()?.message,Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                progressDialog.cancel()
                Toast.makeText(context,t.message,Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun gotoHomeScreen(){
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent).also {
            requireActivity().finish()
        }
    }

    private fun showDialog(){
        progressDialog.setMessage("Checking info")
        progressDialog.show()
    }

    companion object{
        fun newInstance() = Login()
    }
}