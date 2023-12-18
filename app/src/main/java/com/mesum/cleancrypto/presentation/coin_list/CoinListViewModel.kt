package com.mesum.cleancrypto.presentation.coin_list

import android.view.View
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mesum.cleancrypto.common.Resource
import com.mesum.cleancrypto.domain.model.Coin
import com.mesum.cleancrypto.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(private val getCoinsUseCase: GetCoinsUseCase) : ViewModel() {
    private val _state  =  mutableStateOf<CoinListState>(CoinListState())
    val state : State<CoinListState> = _state

    init {
        getCoins()
    }

    private fun getCoins(){
        getCoinsUseCase().onEach {result ->
        when(result){
            is Resource.Success->{
                    _state.value = CoinListState(coins = result.data ?: emptyList())
            }
            is Resource.Error ->{
                    _state.value = CoinListState(error = result.message ?: "Unexpected Error Occured")
            }
            is Resource.Loading ->{
                _state.value = CoinListState(isLoading = true)
            }
        }

        }.launchIn(viewModelScope)
    }


}