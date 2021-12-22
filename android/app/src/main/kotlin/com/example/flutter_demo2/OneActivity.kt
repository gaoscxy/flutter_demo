package com.example.flutter_demo2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle

class OneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one)
    }

    fun turn2flutter(view: android.view.View) {
        val intent = Intent()
        intent.setClass(this,MainActivity::class.java)
        startActivity(intent)
    }
}