package com.app.fromindia.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Data {
    @SerializedName("customer")
    @Expose
    var customer: List<Customer>? = null

}