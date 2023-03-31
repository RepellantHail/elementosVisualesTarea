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
        //Se inicia el boton para abrir otra activity
        sendFilmActivity()
        //Se llena el autotextview y cuando se agrega un valor se muestra con un toast
        val autotextView = findViewById<AutoCompleteTextView>(R.id.edtNombreMovie)
        autoTextAdd(autotextView)
        poblarAutotext(autotextView)

        //Iniciar valor por defecto para radio group
        var radioGroup = findViewById<RadioGroup>(R.id.radioGStatus)
        radioGroup.check(R.id.radioNoVisto)


        //Submit Button
        btnEnviar(autotextView)

    }

    fun abrirFilms(v:View){
        when (v.getId()) {
            R.id.activity_main_btnFilm -> {
                val intent = Intent(this, filmsActivity::class.java)
                startActivity(intent)
            }
    }
    }

    fun autoTextAdd (autotextView:AutoCompleteTextView){//Funcion Para hacer las sugerencias al autocompletar
        autotextView.setOnEditorActionListener{ textView,i,keyEvent ->
            var handled = false
            if(i == EditorInfo.IME_ACTION_DONE){
                //Agregar evento cuando se agregue el nombre de una pelicula
                toastMessage(textView.text.toString())
                handled = true
            }
            handled
        }
    }

    fun poblarAutotext(autotextView:AutoCompleteTextView){
        //Funcion que llena las sugerencias. Saca los datos de un arreglo de strings
        // Get the array of movies
        val movies = resources.getStringArray(R.array.Movies)
        // Create adapter and add in AutoCompleteTextView
        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, movies)
        autotextView.setAdapter(adapter)
    }

    fun sendFilmActivity(){//Funcion que abre otra activity
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
    }

    fun btnEnviar(autotextView:AutoCompleteTextView){//Funcion para el boton de enviar
        val button = findViewById<Button>(R.id.btnAdd)
        if (button != null)
        {
            button ?.setOnClickListener(View.OnClickListener {
                val enteredText = getString(R.string.submitted_lang) + " " + autotextView.getText()
                toastMessage(enteredText)
            })
        }
    }

    fun toastMessage(message:String){
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }
}