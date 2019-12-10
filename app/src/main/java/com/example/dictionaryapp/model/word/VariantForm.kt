package com.example.dictionaryapp.model.word

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VariantForm {
    @SerializedName("domains")
    @Expose
    var domains: List<Domain>? = null
    @SerializedName("notes")
    @Expose
    var notes: List<Note>? = null
    @SerializedName("pronunciations")
    @Expose
    var pronunciations: List<Pronunciation>? = null
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