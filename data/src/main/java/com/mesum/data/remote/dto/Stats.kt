package com.mesum.cleancrypto.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Stats(
    @SerializedName("contributors")
    var contributors: Int? = 0,
    @SerializedName("followers")
    var followers: Int? = 0,
    @SerializedName("stars")
    var stars: Int? = 0,
    @SerializedName("subscribers")
    var subscribers: Int? = 0
)