package com.example.elementosvisualestarea

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class filmsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_films)

        addMovies()

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



}