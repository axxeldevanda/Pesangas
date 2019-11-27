package com.oldlex.pesangas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.oldlex.pesangas.Common.Common

class WelcomeActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        val txtFullName: TextView = findViewById(R.id.txtFullName)
        txtFullName.setText(Common.currentUser.name)

        val btnLanjut: Button = findViewById(R.id.btn_welcome)
        btnLanjut.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.btn_welcome->{
                val homeIntent = Intent(this@WelcomeActivity, Home::class.java)
                startActivity(homeIntent)
            }
        }
    }

}
