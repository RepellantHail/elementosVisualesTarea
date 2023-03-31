package com.example.elementosvisualestarea

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class filmsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_films)

        addMovies()
        addStreming()
    }

    fun addMovies(){
        // Get the array of movies
        val movies = resources.getStringArray(R.array.Movies)
        //Get the Linear Layout
        val linearLayout = findViewById<LinearLayout>(R.id.scrollMovie)
        //For que agrega cada string con el nombre de las movies
        for (entry in movies) {
            val textView = TextView(this)
            textView.text = entry
            linearLayout.addView(textView)
        }
    }

    fun addStreming(){
        val spinner: Spinner = findViewById(R.id.spinner_streaming)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(this,R.array.Streaming,android.R.layout.simple_spinner_item).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
    }

}