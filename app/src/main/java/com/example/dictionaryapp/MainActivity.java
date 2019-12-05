package com.example.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String url;
    private TextView showDef;
    private EditText enterWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showDef = findViewById(R.id.showDef);
        enterWord = findViewById(R.id.enterWord);
    }

    private String dictionaryEntries(){
        final String language = "es";
        final String word = enterWord.getText().toString();
        final String fields = "definitions";
        final String strictMatch = "false";
        final String word_id = word.toLowerCase();

        return "https://od-api.oxforddictionaries.com/api/v2/entries/" + language + "/" + word_id
                + "?" + "fields=" + fields + "&strictMatch=" + strictMatch;
    }

    public void sendRequestOnClick(View v){
        DictionaryRequest dr = new DictionaryRequest(this, showDef);
        url = dictionaryEntries();
        dr.execute(url);
    }
}
