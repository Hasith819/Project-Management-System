package com.nibm.projectmanagementsystem.ui.SplashScreen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager.LayoutParams.*
import androidx.appcompat.app.AppCompatActivity
import com.nibm.projectmanagementsystem.R
import com.nibm.projectmanagementsystem.ui.Authentication.SignActivity
import com.nibm.projectmanagementsystem.ui.Authentication.SignIn.SignInActivity
import com.nibm.projectmanagementsystem.ui.Authentication.SignIn.SignInTabFragment

class SplashScreen : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            FLAG_FULLSCREEN,
            FLAG_FULLSCREEN,
        )
        setContentView(R.layout.activity_splash_screen)
        android.os.Handler().postDelayed(
        {
            val intent = Intent(this@SplashScreen, SignActivity::class.java)
            startActivity(intent)
            finish()
        },
        SPLASH_TIMER.toLong()
        )

    }
    companion object {
        private const val SPLASH_TIMER = 5000
    }
}