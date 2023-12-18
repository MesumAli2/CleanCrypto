package com.mesum.cleancrypto.domain.use_case.get_coins

import com.mesum.cleancrypto.common.Resource
import com.mesum.cleancrypto.data.remote.dto.toCoin
import com.mesum.cleancrypto.domain.model.Coin
import com.mesum.cleancrypto.domain.repository.CoinRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase  @Inject constructor(private val repository: CoinRepository)
    {
    operator fun invoke(): kotlinx.coroutines.flow.Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success(coins))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }
        catch (e: IOException){
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }

}