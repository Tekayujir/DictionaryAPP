package com.example.dictionaryapp.model.word

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Pronunciation {
    @SerializedName("audioFile")
    @Expose
    var audioFile: String? = null
    @SerializedName("dialects")
    @Expose
    var dialects: List<String>? = null
    @SerializedName("phoneticNotation")
    @Expose
    var phoneticNotation: String? = null
    @SerializedName("phoneticSpelling")
    @Expose
    var phoneticSpelling: String? = null
    @SerializedName("regions")
    @Expose
    var regions: List<Region>? = null
    @SerializedName("registers")
    @Expose
    var registers: List<Register>? = null
}