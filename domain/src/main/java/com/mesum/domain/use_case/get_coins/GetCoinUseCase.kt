package com.mesum.domain.use_case.get_coins

import com.mesum.common.common.Resource
import com.mesum.domain.model.Coin
import com.mesum.domain.repository.CoinRepository
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase  @Inject constructor(private val repository: CoinRepository)
    {
    operator fun invoke(): kotlinx.coroutines.flow.Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins().map { it }
            emit(Resource.Success(coins))
        }catch (e: retrofit2.HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }
        catch (e: IOException){
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }

}