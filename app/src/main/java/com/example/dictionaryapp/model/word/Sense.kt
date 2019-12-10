package com.example.dictionaryapp.model.word

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Sense {
    @SerializedName("constructions")
    @Expose
    var constructions: List<Construction>? = null
    @SerializedName("crossReferenceMarkers")
    @Expose
    var crossReferenceMarkers: List<String>? = null
    @SerializedName("crossReferences")
    @Expose
    var crossReferences: List<CrossReference>? = null
    @SerializedName("definitions")
    @Expose
    var definitions: List<String>? = null //<-AQUÍ->
    @SerializedName("domains")
    @Expose
    var domains: List<Domain>? = null
    @SerializedName("etymologies")
    @Expose
    var etymologies: List<String>? = null
    @SerializedName("examples")
    @Expose
    var examples: List<Example>? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
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
    @SerializedName("shortDefinitions")
    @Expose
    var shortDefinitions: List<String>? = null
    @SerializedName("subsenses")
    @Expose
    var subsenses: List<Subsense>? = null
    @SerializedName("thesaurusLinks")
    @Expose
    var thesaurusLinks: List<ThesaurusLink>? = null
    @SerializedName("variantForms")
    @Expose
    var variantForms: List<VariantForm>? = null
}