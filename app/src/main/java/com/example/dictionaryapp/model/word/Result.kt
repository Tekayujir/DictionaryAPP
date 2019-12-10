package com.example.dictionaryapp.model.word

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result {
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("language")
    @Expose
    var language: String? = null
    @SerializedName("lexicalEntries")
    @Expose
    var lexicalEntries: List<LexicalEntry>? = null //-->
    @SerializedName("pronunciations")
    @Expose
    var pronunciations: List<Pronunciation>? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("word")
    @Expose
    var word: String? = null
}