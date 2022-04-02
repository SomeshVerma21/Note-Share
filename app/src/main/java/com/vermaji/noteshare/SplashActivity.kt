package com.vermaji.noteshare

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import com.vermaji.noteshare.loginService.LoginActivity
import com.vermaji.noteshare.loginService.session.SessionManagement
import com.vermaji.noteshare.mainUI.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.attributes.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        supportActionBar?.hide()
        setupUI()
    }

    private fun setupUI()
    {
        Handler().postDelayed(Runnable {
            if (SessionManagement(this).checkUserLogin()){
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }else
            {
                val intent  = Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }
            finish()
        },2000)
    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                )
    }
}