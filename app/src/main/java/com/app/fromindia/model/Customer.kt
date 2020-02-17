package com.app.fromindia.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Customer {
    @SerializedName("customer_id")
    @Expose
    var customerId: String? = null
    @SerializedName("hash")
    @Expose
    var hash: String? = null
    @SerializedName("bearer_token")
    @Expose
    var bearerToken: String? = null
    @SerializedName("customer_group")
    @Expose
    var customerGroup: String? = null
    @SerializedName("cart_summary")
    @Expose
    var cartSummary: Int? = null
    @SerializedName("gender")
    @Expose
    var gender: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("existing_user")
    @Expose
    var existingUser: Boolean? = null
    @SerializedName("gift_avail")
    @Expose
    var giftAvail: Boolean? = null
    // For Getting message -- not success
    @SerializedName("message")
    @Expose
    var message: String? = null

    // For Registration value getting
    @SerializedName("isConfirmationRequired")
    @Expose
    var isConfirmationRequired: String? = null

}