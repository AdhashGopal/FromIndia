package com.app.fromindia.model.SlideMenu

import com.app.fromindia.model.Data
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MenuList {
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("data")
    @Expose
    var data: Data? = null

}