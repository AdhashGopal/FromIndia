package com.app.fromindia.model.SlideMenu

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SubCat {
    @SerializedName("sub_category_id")
    @Expose
    var subCategoryId: String? = null
    @SerializedName("sub_category_name")
    @Expose
    var subCategoryName: String? = null
    @SerializedName("sub_category_image")
    @Expose
    var subCategoryImage: Boolean? = null
    @SerializedName("sort_order")
    @Expose
    var sortOrder: String? = null
    @SerializedName("has_child")
    @Expose
    var hasChild: Boolean? = null

}