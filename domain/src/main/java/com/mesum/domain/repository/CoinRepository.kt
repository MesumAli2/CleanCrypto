package com.mesum.domain.repository


import com.mesum.domain.model.Coin
import com.mesum.domain.model.CoinDetail

interface CoinRepository {
    suspend fun  getCoins(): List<Coin>

    suspend fun getCoinById(coinId: String): CoinDetail
}