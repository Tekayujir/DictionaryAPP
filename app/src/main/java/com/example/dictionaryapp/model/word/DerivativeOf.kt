package com.example.dictionaryapp.model.word

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DerivativeOf {
    @SerializedName("domains")
    @Expose
    var domains: List<Domain>? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("language")
    @Expose
    var language: String? = null
    @SerializedName("regions")
    @Expose
    var regions: List<Region>? = null
    @SerializedName("registers")
    @Expose
    var registers: List<Register>? = null
    @SerializedName("text")
    @Expose
    var text: String? = null
}