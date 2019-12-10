package com.example.dictionaryapp.model.word

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LexicalEntry {
    @SerializedName("derivativeOf")
    @Expose
    var derivativeOf: List<DerivativeOf>? = null
    @SerializedName("derivatives")
    @Expose
    var derivatives: List<Derivative>? = null
    @SerializedName("entries")
    @Expose
    var entries: List<Entry>? = null //-->
    @SerializedName("grammaticalFeatures")
    @Expose
    var grammaticalFeatures: List<GrammaticalFeature>? = null
    @SerializedName("language")
    @Expose
    var language: String? = null
    @SerializedName("lexicalCategory")
    @Expose
    var lexicalCategory: LexicalCategory? = null
    @SerializedName("notes")
    @Expose
    var notes: List<Note>? = null
    @SerializedName("pronunciations")
    @Expose
    var pronunciations: List<Pronunciation>? = null
    @SerializedName("text")
    @Expose
    var text: String? = null
    @SerializedName("variantForms")
    @Expose
    var variantForms: List<VariantForm>? = null
}