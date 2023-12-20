package com.mesum.data.repository

import com.mesum.data.remote.CoinPaprikaApi
import com.mesum.data.remote.dto.toCoinDetail
import com.mesum.domain.model.Coin
import com.mesum.domain.model.CoinDetail
import com.mesum.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(private val api: CoinPaprikaApi): CoinRepository {
    override suspend fun getCoins(): List<Coin> {
        return api.getCoins().map { coinDto ->
            Coin(id = coinDto.id, isActive =  coinDto.isActive, name = coinDto.name, symbol = coinDto.symbol, rank = coinDto.rank)
        }
    }

    override suspend fun getCoinById(coinId: String): CoinDetail {
        return api.getCoin(coinId = coinId).toCoinDetail()
    }
}