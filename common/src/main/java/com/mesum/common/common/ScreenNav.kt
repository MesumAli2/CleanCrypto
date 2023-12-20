package com.mesum.common.common

sealed class ScreenNav(val route: String){
    object CoinListScreenNav: ScreenNav("coin_list_screen")
    object CoinDetailScreenNav: ScreenNav("coin_detail_screen")
}