package com.example.dictionaryapp

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.dictionaryapp.model.word.Example
import com.example.dictionaryapp.roomDB.DBSwitch
import com.example.dictionaryapp.roomDB.EntitySwitch
import com.example.dictionaryapp.services.ServiceConfiguration
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private var showDef: TextView? = null
    private var enterWord: EditText? = null
    private var idioma: String = "es"
    private var textoError: Int = R.string.errorFind
    private var textoErrorInterneto: Int = R.string.textoErrorInterneto

    //Room
    private var db: DBSwitch? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(applicationContext, DBSwitch::class.java, "isChecked").allowMainThreadQueries().build()

        showDef = findViewById(R.id.showDef)
        enterWord = findViewById(R.id.enterWord)

        // Al pulsar el botón INTRO del teclado
        with(enterWord) {
            this?.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    sendRequestOnClick(this.text.toString(), idioma)
                    closeKeyboard()

                    //return@OnKeyListener true
                    true
                } else false
            })
        }

        // Al pulsar el botón BUSCAR/SEARCH
        button.setOnClickListener {
            sendRequestOnClick(enterWord?.text.toString(), idioma)
        }


        // Primera vez que inicia la APP, si no contiene ningún valor (true o false), inserta false
        if(db?.daoSwitch()?.getChecked()?.isEmpty()!!){
            db?.daoSwitch()?.insertChecked(EntitySwitch(0, false))
        }

        // Cambio de tema - Conserva el estado al cerrar la APP
        if(db?.daoSwitch()?.getChecked()?.first()?.checked!!){
            darkTheme()
        }else{
            whiteTheme()
        }
    }

    /**
     * Menú Toolbar
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)

        // Botón switch - Cambio de color del tema de la app
        val item = menu.findItem(R.id.action_one) as MenuItem
        val switchMenu = item.actionView.findViewById<Switch>(R.id.action_one)

        // Mantiene el Switch en su posición
        switchMenu.isChecked = db?.daoSwitch()?.getChecked()?.first()?.checked!!

        switchMenu.setOnCheckedChangeListener { _ , isChecked ->
            if (isChecked) {
                darkTheme()
            }
            else {
                whiteTheme()
            }
            // Almacena el boolean al pulsar el switch
            db?.daoSwitch()?.updateChecked(EntitySwitch(0, isChecked))
        }
        return true
    }

    fun darkTheme(){
        fondito.setBackgroundColor(this.resources.getColor(R.color.colorBackgroundDark))
        showDef?.setTextColor(this.resources.getColor(R.color.colorShowDefWhite))
        enterWord?.setHintTextColor(this.resources.getColor(R.color.colorEnterWordDark))
        enterWord?.setTextColor(this.resources.getColor(R.color.colorShowDefDark))
    }
    fun whiteTheme(){
        fondito.setBackgroundColor(this.resources.getColor(R.color.colorBackground))
        showDef?.setTextColor(this.resources.getColor(R.color.colorShowDef))
        enterWord?.setHintTextColor(this.resources.getColor(R.color.colorShowDef))
        enterWord?.setTextColor(this.resources.getColor(R.color.colorPrimary))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        val id = item.itemId

        // MI ESPAÑITA!!
        if (id == R.id.action_two) {
            idioma = "es"

            // Cambia los textos a español
            textView.setText(R.string.intro)
            this.enterWord?.setHint(R.string.hide)
            button.setText(R.string.boton)

            showDef?.setText("")
            //enterWord?.setText("")

            textoError = R.string.errorFind
            textoErrorInterneto = R.string.textoErrorInterneto

            return true
        }
        // FISH AND CHIPS
        if (id == R.id.action_three) {
            idioma = "en-gb"

            // Cambia los textos a inglés
            textView.setText(R.string.introEN)
            this.enterWord?.setHint(R.string.hideEN)
            button.setText(R.string.botonEN)

            showDef?.setText("")
            //enterWord?.setText("")

            textoError = R.string.errorFindEN
            textoErrorInterneto = R.string.textoErrorInternetoEN

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

        // Comprobando conexión a internet
        onNetworkChange()

        call?.enqueue(object : Callback<Example> {
            override fun onResponse(call: Call<Example>, response: Response<Example>) {
                if (response.isSuccessful) {
                    // llamada OK
                    val example = response.body()
                    val def: String

                    def = example.results?.get(0)?.lexicalEntries?.get(0)?.entries?.get(0)?.senses?.get(0)?.definitions?.get(0).toString()
                    showDef?.text = "$def"

                    Log.d("retrofit", "result size: ${example.results?.size}")
                } else {
                    // llamada KO
                    showDef?.text = ""
                    Tostarica(textoError)

                    Log.e("retrofit", "return: ${response.errorBody()}")
                }
            }
            override fun onFailure(call: Call<Example>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    /**
     * Oculta el teclado
     */
    private fun closeKeyboard(){
        var view: View? = this.currentFocus
        if(view != null){
            var imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    /**
     * Comprueba la conexión a internet
     */
    private fun onNetworkChange() {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo

        if (networkInfo != null && networkInfo.isConnected) { // Si hay conexión a Internet en este momento
        } else { // No hay conexión a Internet en este momento
            Tostarica(textoErrorInterneto)
        }
    }

    /**
     * Muestra un Toast
     */
    private fun Tostarica(texto: Int){
        val toast = Toast.makeText(applicationContext,
                texto, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 270)
        toast.show()
    }
}