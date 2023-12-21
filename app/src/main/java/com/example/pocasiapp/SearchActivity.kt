package com.example.pocasiapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class SearchActivity : AppCompatActivity() {
    //var fruits = arrayOf("Apple", "Banana", "Cherry", "Date", "Grape", "Kiwi", "Mango", "Pear")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val cities: Array<String> = resources.getStringArray(R.array.cities)
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, cities)

        val actv = findViewById<View>(R.id.autoCompleteTextView) as AutoCompleteTextView
        actv.threshold = 2
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView


        val btn = findViewById<Button>(R.id.button)

        btn.setOnClickListener(){
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("city", actv.text.toString())
            startActivity(intent)
        }

    }


}