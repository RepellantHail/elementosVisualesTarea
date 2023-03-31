package com.example.elementosvisualestarea

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Button to Movie List
        var btnFilm: Button
        btnFilm = findViewById(R.id.activity_main_btnFilm)
        btnFilm.setOnClickListener(object : View.OnClickListener{
            override fun onClick(view: View?) {
                if (view != null) {
                    abrirFilms(view)
                }
            }
        })


        val autotextView = findViewById<AutoCompleteTextView>(R.id.edtNombreMovie)
        autotextView.setOnEditorActionListener{ textView,i,keyEvent ->
            var handled = false
            if(i == EditorInfo.IME_ACTION_DONE){
                //Agregar evento cuando se agregue el nombre de una pelicula
                handled = true
            }
            handled
        }
        // Get the array of movies
        val movies = resources.getStringArray(R.array.Movies)
        // Create adapter and add in AutoCompleteTextView
        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, movies)
        autotextView.setAdapter(adapter)

        var radioGroup = findViewById<RadioGroup>(R.id.radioGStatus)
        radioGroup.check(R.id.radioNoVisto)


        //Submit Button
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

    fun abrirFilms(v:View){
        when (v.getId()) {
            R.id.activity_main_btnFilm -> {
                val intent = Intent(this, filmsActivity::class.java)
                startActivity(intent)
            }
    }
    }
}