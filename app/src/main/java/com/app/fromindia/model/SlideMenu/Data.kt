package com.app.fromindia.model.SlideMenu

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Data {
    @SerializedName("categories")
    @Expose
    var categories: List<Category>? = null
    @SerializedName("special_categories")
    @Expose
    var specialCategories: List<SpecialCategory>? = null

}