package com.vermaji.noteshare.loginService

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vermaji.noteshare.R
import com.vermaji.noteshare.loginService.fragments.Login

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