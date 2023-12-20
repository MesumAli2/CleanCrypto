package com.mesum.domain.use_case.get_coin

import com.mesum.common.common.Resource
import com.mesum.domain.model.CoinDetail
import com.mesum.domain.repository.CoinRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase  @Inject constructor(private val repository: CoinRepository)
    {
    operator fun invoke(coinId: String): kotlinx.coroutines.flow.Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoinById(coinId)
            emit(Resource.Success<CoinDetail>(coin))
        }catch (e: HttpException){
            emit(Resource.Error<CoinDetail>(e.localizedMessage ?: "An unexpected error occured"))
        }
        catch (e: IOException){
            emit(Resource.Error<CoinDetail>("Couldn't reach server. Check your internet connection"))
        }
    }

}