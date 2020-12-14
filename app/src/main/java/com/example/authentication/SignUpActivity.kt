package com.example.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlin.math.sign

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        init()

    }

    private fun init(){
        auth = Firebase.auth
        signupbutton1.setOnClickListener(){
            signUp()

        }
    }

    private fun signUp(){
        val email = emailedittext.text.toString()
        val password = passtext.text.toString()
        val repeatpassword = passtext2.text.toString()

        if(email.isNotEmpty() && password.isNotEmpty() && repeatpassword.isNullOrEmpty()){
            if(password == repeatpassword){
                progressbar.visibility = View.VISIBLE
                signupbutton1.isClickable = false
                if ("@gmail.com" in email) {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                progressbar.visibility = View.GONE
                                signupbutton1.isClickable = true
                                Toast.makeText(this, "Sign up is successful!", Toast.LENGTH_SHORT)
                                    .show()
                                // Sign in success, update UI with the signed-in user's information
                                d("signup", "createUserWithEmail:success")
                                val user = auth.currentUser

                            } else {
                                val reason = task.exception
                                progressbar.visibility = View.GONE
                                signupbutton1.isClickable = true
                                // If sign in fails, display a message to the user.
                                d("signup", "createUserWithEmail:failure", task.exception)
                                Toast.makeText(
                                    baseContext, "$reason",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }

                            // ...
                        }
                }else{
                    Toast.makeText(this, "Email format is not correct!", Toast.LENGTH_SHORT).show()
                }

            }else{
                Toast.makeText(this, "Passwords are not correct!", Toast.LENGTH_SHORT).show()
            }

        }else{
            Toast.makeText(this, "Please, fill all the fields!", Toast.LENGTH_SHORT).show()
        }
    }
}