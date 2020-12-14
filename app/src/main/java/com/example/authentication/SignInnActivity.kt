package com.example.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_inn.*
import kotlinx.android.synthetic.main.activity_sign_inn.progressbar
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignInnActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_inn)
        init()

    }
    private fun init(){
        auth = Firebase.auth
        signinbutton1.setOnClickListener(){
            signIn()

        }

    }
    private fun signIn(){
        val email = emailedittext1.text.toString()
        val password = signinpasstext.text.toString()
        if (email.isNotEmpty() && password.isNullOrEmpty()){
            progressbar.visibility = View.VISIBLE
            signinbutton1.isClickable = false
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        progressbar.visibility = View.GONE
                        signinbutton1.isClickable = true
                        Toast.makeText(baseContext, "Authentication is successful!",
                            Toast.LENGTH_SHORT).show()
                        // Sign in success, update UI with the signed-in user's information
                        d("signin", "signInWithEmail:success")

                    } else {
                        val reason = task.exception
                        progressbar.visibility = View.GONE
                        signinbutton1.isClickable = true
                        // If sign in fails, display a message to the user.
                        d("signin", "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "$reason",
                            Toast.LENGTH_SHORT).show()

                        // ...
                    }

                    // ...
                }
        }else{
            Toast.makeText(this, "Please, fill all the fields!", Toast.LENGTH_SHORT).show()
        }
    }
}