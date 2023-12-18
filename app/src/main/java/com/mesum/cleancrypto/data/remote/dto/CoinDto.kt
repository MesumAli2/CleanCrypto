package com.mesum.cleancrypto.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.mesum.cleancrypto.domain.model.Coin

data class CoinDto(
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("is_active")
    var isActive: Boolean? = false,
    @SerializedName("is_new")
    var isNew: Boolean? = false,
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("rank")
    var rank: Int? = 0,
    @SerializedName("symbol")
    var symbol: String? = "",
    @SerializedName("type")
    var type: String? = ""
)

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        name = name,
        symbol =  symbol,
        isActive = isActive,
        rank = rank
    )
}