package com.example.dictionaryapp.model.word

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Example {
    //@SerializedName("metadata")
    //@Expose
    //var metadata: Metadata? = null
    @SerializedName("results")
    @Expose
    var results: List<Result>? = null
}