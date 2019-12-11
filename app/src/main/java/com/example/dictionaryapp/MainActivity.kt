package com.example.dictionaryapp

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.dictionaryapp.model.word.Example
import com.example.dictionaryapp.services.ServiceConfiguration
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private var showDef: TextView? = null
    private var enterWord: EditText? = null
    private var idioma: String = "es"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showDef = findViewById(R.id.showDef)
        enterWord = findViewById(R.id.enterWord)

        with(enterWord) {
            this?.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    sendRequestOnClick(this?.text.toString(), idioma)
                    return@OnKeyListener true
                } else false
            })
        }

        button.setOnClickListener {
            sendRequestOnClick(enterWord?.text.toString(), idioma)
        }
    }

    /**
     * Menú
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        val id = item.itemId

        // COLOR TEMA
        if (id == R.id.action_one) {

            // CAMBIAR LOS COLORES DEL TEMA DE LA APLICACION

            return true
        }
        // MI ESPAÑITA!!
        if (id == R.id.action_two) {
            idioma = "es"

            textView.setText(R.string.intro)
            this.enterWord?.setHint(R.string.hide)
            button.setText(R.string.boton)

            return true
        }
        // FISH AND CHIPS
        if (id == R.id.action_three) {
            idioma = "en-gb"

            textView.setText(R.string.introEN)
            this.enterWord?.setHint(R.string.hideEN)
            button.setText(R.string.botonEN)

            return true
        }

        return super.onOptionsItemSelected(item)
    }

    /**
     * Acción al pulsar el botón BUSCAR/SEARCH
     */
    private fun sendRequestOnClick(word: String, idioma: String) {
        val service = ServiceConfiguration().getService()
        val call = service.getWord(word, idioma)

        call?.enqueue(object : Callback<Example> {
            override fun onResponse(call: Call<Example>, response: Response<Example>) {
                if (response.isSuccessful) {
                    // llamada OK
                    val example = response.body()
                    val def: String

                    def = example.results?.get(0)?.lexicalEntries?.get(0)?.entries?.get(0)?.senses?.get(0)?.definitions?.get(0).toString()
                    showDef?.text = "$def."

                    Log.d("retrofit", "result size: ${example.results?.size}")
                } else {
                    // llamada KO

                    Log.e("retrofit", "return: ${response.errorBody()}")
                }
            }
            override fun onFailure(call: Call<Example>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}