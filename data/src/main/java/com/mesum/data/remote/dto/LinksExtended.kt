package com.mesum.cleancrypto.data.remote.dto


import com.google.gson.annotations.SerializedName

data class LinksExtended(
    @SerializedName("stats")
    var stats: Stats? = Stats(),
    @SerializedName("type")
    var type: String? = "",
    @SerializedName("url")
    var url: String? = ""
)