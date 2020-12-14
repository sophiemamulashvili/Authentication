package com.example.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class AuthenticationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init(){
        signinbutton.setOnClickListener(){
            val intent = Intent(this, SignInnActivity::class.java)
            startActivity(intent)

        }
        signupbutton.setOnClickListener(){
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}