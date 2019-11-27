package com.oldlex.pesangas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.*
import com.oldlex.pesangas.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_signup.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnHomeLogin: Button = findViewById(R.id.btnHomeLogin)
        btnHomeLogin.setOnClickListener(this)

        val txtRegister: TextView = findViewById(R.id.txtRegister)
        txtRegister.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnHomeLogin -> {
                val signinIntent = Intent(this@MainActivity, SigninActivity::class.java)
                startActivity(signinIntent)
            }
            R.id.txtRegister -> {
                val signupIntent = Intent(this@MainActivity, SignupActivity::class.java)
                startActivity(signupIntent)
            }
        }
    }

}

