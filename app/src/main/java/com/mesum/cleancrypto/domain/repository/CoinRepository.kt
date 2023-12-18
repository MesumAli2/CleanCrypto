package com.mesum.cleancrypto.domain.repository

import com.mesum.cleancrypto.data.remote.dto.CoinDetailDto
import com.mesum.cleancrypto.data.remote.dto.CoinDto

interface CoinRepository {
    suspend fun  getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}