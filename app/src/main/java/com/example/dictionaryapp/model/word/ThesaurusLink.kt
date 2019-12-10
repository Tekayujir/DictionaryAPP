package com.example.dictionaryapp.model.word

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ThesaurusLink {
    @SerializedName("entry_id")
    @Expose
    var entryId: String? = null
    @SerializedName("sense_id")
    @Expose
    var senseId: String? = null
}