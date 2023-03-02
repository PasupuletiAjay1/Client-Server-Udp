package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT > 9) {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Client button
        val buttonClickA = findViewById<Button>(R.id.client)
        buttonClickA.setOnClickListener {
            val intent = Intent(this, Client::class.java)
            startActivity(intent)
        }

        //Server button
        val buttonClickB = findViewById<Button>(R.id.server)
        buttonClickB.setOnClickListener {
            val intent = Intent(this, Server::class.java)
            startActivity(intent)
        }
    }
}