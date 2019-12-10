package com.example.dictionaryapp

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showDef = findViewById(R.id.showDef)
        enterWord = findViewById(R.id.enterWord)

        with(enterWord) {
            this?.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    sendRequestOnClick(this?.text.toString())
                    return@OnKeyListener true
                } else false
            })
        }

        button.setOnClickListener {
            sendRequestOnClick(enterWord?.text.toString())
        }
    }

    /**
     * Acción al pulsar el botón
     */
    private fun sendRequestOnClick(word: String) {
        val service = ServiceConfiguration().getService()
        val call = service.getWord(word)

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