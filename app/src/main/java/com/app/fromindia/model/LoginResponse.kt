package com.app.fromindia.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginResponse {
    @SerializedName("data")
    @Expose
    var data: Data? = null

}