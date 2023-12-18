package com.mesum.cleancrypto.domain.use_case.get_coin

import com.mesum.cleancrypto.common.Resource
import com.mesum.cleancrypto.data.remote.dto.toCoinDetail
import com.mesum.cleancrypto.domain.model.CoinDetail
import com.mesum.cleancrypto.domain.repository.CoinRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase  @Inject constructor(private val repository: CoinRepository)
    {
    operator fun invoke(coinId: String): kotlinx.coroutines.flow.Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success(coin))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }
        catch (e: IOException){
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }

}