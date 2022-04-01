package com.vermaji.notebook.loginService

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vermaji.notebook.R
import com.vermaji.notebook.loginService.fragments.Login

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupUI()
    }

    private fun setupUI()
    {
        supportFragmentManager.beginTransaction()
            .replace(R.id.idLoginFragmentNavigation, Login.newInstance())
            .commit()
    }
}