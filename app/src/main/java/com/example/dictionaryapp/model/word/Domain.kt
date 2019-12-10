package com.example.dictionaryapp.model.word

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Domain {
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("text")
    @Expose
    var text: String? = null
}