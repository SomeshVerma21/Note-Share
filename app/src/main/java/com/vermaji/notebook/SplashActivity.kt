package com.vermaji.notebook

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.vermaji.notebook.loginService.LoginActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupUI()
    }

    private fun setupUI()
    {
        Handler().postDelayed(Runnable {
            val intent  = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        },1000)
    }
}