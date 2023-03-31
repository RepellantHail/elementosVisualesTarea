package com.example.elementosvisualestarea

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Se inicia el boton para abrir otra activity
        sendFilmActivity()

        //Se llena el autotextview y cuando se agrega un valor se muestra con un toast
        val autotextView = findViewById<AutoCompleteTextView>(R.id.edtNombreMovie)
        autotextView.threshold = 1  //Set threshhold to 1
        autoTextAdd(autotextView)
        poblarAutotext(autotextView)
        //Evento Cuando Se selecciona un  elemento
        autoTextSelectItem(autotextView)

        //CheckBox
        checkInitialize()
        //RadioGroup
        radioGroupInitialize()
        //Submit Button
        btnEnviar(autotextView)

    }



    fun radioGroupInitialize(){
        //Iniciar valor por defecto para radio group
        var radioGroup = findViewById<RadioGroup>(R.id.radioGStatus)
        radioGroup.check(R.id.radioNoVisto)

        val radioButton1 = findViewById<RadioButton>(R.id.radioVisto)
        val radioButton2 = findViewById<RadioButton>(R.id.radioWatchlist)
        val radioButton3 = findViewById<RadioButton>(R.id.radioNoVisto)

        radioButton1.setOnClickListener {
            Toast.makeText(this, "Muy bien, conocerdor!", Toast.LENGTH_SHORT).show()
        }
        radioButton2.setOnClickListener {
            Toast.makeText(this, "Preparate para lloar! :c", Toast.LENGTH_SHORT).show()
        }
        radioButton3.setOnClickListener {
            Toast.makeText(this, "Hmmm..., esperaba más de ti", Toast.LENGTH_SHORT).show()
        }

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            // Handle the checked state change event here
            val radioButton = findViewById<RadioButton>(checkedId)
            Toast.makeText(this, "${radioButton.text} checked", Toast.LENGTH_SHORT).show()
        }

    }

    fun autoTextSelectItem(autotextView:AutoCompleteTextView){
        autotextView.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position) as String
            Toast.makeText(this@MainActivity, "Selected item: $selectedItem", Toast.LENGTH_SHORT).show()
            println("Pelicula seleccionada: $selectedItem")
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

    fun checkInitialize(){
        val checkbox1 = findViewById<CheckBox>(R.id.checkBox1)
        val checkbox2 = findViewById<CheckBox>(R.id.checkBox2)
        val checkbox3 = findViewById<CheckBox>(R.id.checkBox3)
        val checkbox4 = findViewById<CheckBox>(R.id.checkBox4)
        val checkbox5 = findViewById<CheckBox>(R.id.checkBox5)

        val checkboxListener = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            val checkboxText = buttonView.text
            if (isChecked) {
                // Do something when the checkbox is checked
                Toast.makeText(this@MainActivity, "$checkboxText esta marcada", Toast.LENGTH_SHORT).show()
            } else {
                // Do something when the checkbox is unchecked
                Toast.makeText(this@MainActivity, "$checkboxText desmarcada", Toast.LENGTH_SHORT).show()
            }
        }

        val checkBoxes = listOf(checkbox1, checkbox2, checkbox3,checkbox4,checkbox5)

        // Set the OnLongClickListener event for each CheckBox instance
        checkBoxes.forEach { checkBox ->
            checkBox.setOnLongClickListener {
                // Handle the long-click event here
                Toast.makeText(this, "Long-clicked on ${checkBox.text}", Toast.LENGTH_SHORT).show()
                true
            }
        }

        checkbox1.setOnCheckedChangeListener(checkboxListener)
        checkbox2.setOnCheckedChangeListener(checkboxListener)
        checkbox3.setOnCheckedChangeListener(checkboxListener)
        checkbox4.setOnCheckedChangeListener(checkboxListener)
        checkbox5.setOnCheckedChangeListener(checkboxListener)
    }

    fun autoTextAdd (autotextView:AutoCompleteTextView){//Funcion Para hacer las sugerencias al autocompletar
        autotextView.setOnEditorActionListener{ textView,i,keyEvent ->
            var handled = false

            if(i == EditorInfo.IME_ACTION_DONE){
                //Esconder Teclado
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(autotextView.windowToken, 0)
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