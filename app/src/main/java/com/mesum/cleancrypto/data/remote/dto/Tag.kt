package com.mesum.cleancrypto.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Tag(
    @SerializedName("coin_counter")
    var coinCounter: Int? = 0,
    @SerializedName("ico_counter")
    var icoCounter: Int? = 0,
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("name")
    var name: String? = ""
)