package com.example.pocasiapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val editText = findViewById<EditText>(R.id.editText)
        val btn = findViewById<Button>(R.id.button)

        btn.setOnClickListener(){
            val intent = Intent(this, ResultActivity::class.java)
            //intent.putExtra("city", )
            startActivity(intent)
        }

    }


}