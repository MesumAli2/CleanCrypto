package com.mesum.cleancrypto.data.repository

import com.mesum.cleancrypto.data.remote.CoinPaprikaApi
import com.mesum.cleancrypto.data.remote.dto.CoinDetailDto
import com.mesum.cleancrypto.data.remote.dto.CoinDto
import com.mesum.cleancrypto.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(private val api: CoinPaprikaApi): CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoin(coinId = coinId)
    }
}