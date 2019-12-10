package com.example.dictionaryapp.model.word

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Entry {
    @SerializedName("etymologies")
    @Expose
    var etymologies: List<String>? = null
    @SerializedName("grammaticalFeatures")
    @Expose
    var grammaticalFeatures: List<GrammaticalFeature>? = null
    @SerializedName("homographNumber")
    @Expose
    var homographNumber: String? = null
    @SerializedName("notes")
    @Expose
    var notes: List<Note>? = null
    @SerializedName("pronunciations")
    @Expose
    var pronunciations: List<Pronunciation>? = null
    @SerializedName("senses")
    @Expose
    var senses: List<Sense>? = null //-->
    @SerializedName("variantForms")
    @Expose
    var variantForms: List<VariantForm>? = null
}