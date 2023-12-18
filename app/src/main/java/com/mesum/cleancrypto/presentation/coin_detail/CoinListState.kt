package com.mesum.cleancrypto.presentation.coin_detail

import com.mesum.cleancrypto.domain.model.Coin
import com.mesum.cleancrypto.domain.model.CoinDetail

data class CoinDetailState (
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)