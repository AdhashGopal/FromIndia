package com.app.fromindia.model.SlideMenu

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Category {
    @SerializedName("main_category_id")
    @Expose
    var mainCategoryId: String? = null
    @SerializedName("main_category_name")
    @Expose
    var mainCategoryName: String? = null
    @SerializedName("main_category_image")
    @Expose
    var mainCategoryImage: String? = null
    @SerializedName("sort_order")
    @Expose
    var sortOrder: String? = null
    @SerializedName("has_child")
    @Expose
    var hasChild: Boolean? = null
    @SerializedName("sub_cats")
    @Expose
    var subCats: List<SubCat>? = null

}