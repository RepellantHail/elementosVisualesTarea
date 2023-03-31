package com.example.elementosvisualestarea

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.google.android.material.switchmaterial.SwitchMaterial


class filmsActivity : AppCompatActivity() {
    private var isSpinnerInitialized = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_films)

        addMovies()
        addStreming()
        intializeToggle()
        eventsSpinner()
    }

    fun intializeToggle(){
        val toggleButtonEdad = findViewById<SwitchCompat>(R.id.switchcompat)
        toggleButtonEdad.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                Toast.makeText(this@filmsActivity,"Bienvenido! Persona Adulta",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@filmsActivity,"Bienvenido! Bebé. Aquí tenemos Caricaturas para ti",Toast.LENGTH_SHORT).show()
            }
        }

        val toggleButtonRegion = findViewById<SwitchMaterial>(R.id.material_switch)
        toggleButtonRegion.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                Toast.makeText(this@filmsActivity,"Parlez-vous français?",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@filmsActivity,"Viva, México!!!",Toast.LENGTH_SHORT).show()
            }
        }
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

    fun eventsSpinner(){
        val spinner: Spinner = findViewById(R.id.spinner_streaming)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (isSpinnerInitialized) {
                    val selectedItem = parent?.getItemAtPosition(position).toString()
                    Toast.makeText(this@filmsActivity, "Selected item: $selectedItem", Toast.LENGTH_SHORT).show()
                } else {
                    isSpinnerInitialized = true
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

    }

}