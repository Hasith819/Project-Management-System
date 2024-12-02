package com.nibm.projectmanagementsystem.ui.Authentication.SignUp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nibm.projectmanagementsystem.R
import com.nibm.projectmanagementsystem.databinding.ActivitySignUpBinding
import com.nibm.projectmanagementsystem.ui.Authentication.SignIn.SignInActivity

class SignUpActivity : Activity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.HaveAccount.setOnClickListener{
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }





    }
}


