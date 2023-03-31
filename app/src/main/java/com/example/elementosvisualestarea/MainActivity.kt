package com.example.elementosvisualestarea

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val autotextView = findViewById<AutoCompleteTextView>(R.id.edtNombreMovie)
        // Get the array of movies
        val movies = resources.getStringArray(R.array.Movies)
        // Create adapter and add in AutoCompleteTextView
        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, movies)
        autotextView.setAdapter(adapter)

        val button = findViewById<Button>(R.id.btnAdd)
        if (button != null)
        {
            button ?.setOnClickListener(View.OnClickListener {
                val enteredText = getString(R.string.submitted_lang) + " " + autotextView.getText()
                //Toast.makeText(applicationContext,"Your response has been recorded", Toast.LENGTH_LONG).show() //This line just show a toast
            Toast.makeText(this@MainActivity, enteredText, Toast.LENGTH_SHORT).show()
            })
        }

    }
}