package com.mesum.data.remote

import com.mesum.data.remote.dto.CoinDetailDto
import com.mesum.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {

     @GET("/v1/coins")
     suspend fun getCoins(): List<CoinDto>

     @GET("/v1/coins/{coinId}")
     suspend fun getCoin(@Path("coinId") coinId: String): CoinDetailDto
}