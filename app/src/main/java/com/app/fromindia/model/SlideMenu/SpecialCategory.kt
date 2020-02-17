package com.app.fromindia.model.SlideMenu

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SpecialCategory {
    @SerializedName("special_category_id")
    @Expose
    var specialCategoryId: String? = null
    @SerializedName("special_category_name")
    @Expose
    var specialCategoryName: Any? = null
    @SerializedName("has_child")
    @Expose
    var hasChild: Boolean? = null

}