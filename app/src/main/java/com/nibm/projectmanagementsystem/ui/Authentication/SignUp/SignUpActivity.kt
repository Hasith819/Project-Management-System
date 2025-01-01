package com.nibm.projectmanagementsystem.ui.Authentication.SignUp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.nibm.projectmanagementsystem.R
import com.nibm.projectmanagementsystem.databinding.ActivitySignUpBinding
import com.nibm.projectmanagementsystem.ui.Authentication.SignIn.SignInActivity

class SignUpActivity : AppCompatActivity() {


    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        database = FirebaseFirestore.getInstance()

        binding.HaveAccount.setOnClickListener{
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        binding.BtnSignIn.setOnClickListener{
            val user = binding.User.text.toString().trim()
            val email = binding.EmailAddress.text.toString().trim()
            val password = binding.Password.text.toString().trim()
            val confirmPassword = binding.ConfirmPassword.text.toString().trim()

           if(user.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty())
           {
               if (password == confirmPassword) {
                   // Sign up the user
                   firebaseAuth.createUserWithEmailAndPassword(email, password)
                       .addOnCompleteListener {
                           if (it.isSuccessful) {
                               // Save additional user data to Firestore
                               val userId = firebaseAuth.currentUser?.uid
                               val userMap = hashMapOf(
                                   "username" to user,
                                   "email" to email
                               )
                               if (userId != null) {
                                   database.collection("users").document(userId)
                                       .set(userMap)
                                       .addOnSuccessListener {
                                           Toast.makeText(this, "Sign-up successful! Please log in.", Toast.LENGTH_SHORT).show()
                                           val intent = Intent(this, SignInActivity::class.java)
                                           startActivity(intent)
                                       }
                                       .addOnFailureListener { e ->
                                           Toast.makeText(this, "Error saving user info: ${e.message}", Toast.LENGTH_SHORT).show()
                                       }
                               }
                           } else {
                               Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
                           }
                       }
               } else {
                   Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
               }
           } else {
               Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
           }
        }
    }
}
