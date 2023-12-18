package com.mesum.cleancrypto.data.remote

import com.mesum.cleancrypto.data.remote.dto.CoinDetailDto
import com.mesum.cleancrypto.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {

     @GET("/v1/coins")
     suspend fun getCoins(): List<CoinDto>

     @GET("/v1/coins/{coinId}")
     suspend fun getCoin(@Path("coinId") coinId: String): CoinDetailDto
}