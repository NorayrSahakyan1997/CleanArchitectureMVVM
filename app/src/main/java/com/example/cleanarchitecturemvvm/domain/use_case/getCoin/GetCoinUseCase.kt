package com.example.cleanarchitecturemvvm.domain.use_case.getCoin
import com.example.cleanarchitecturemvvm.common.Resource
import com.example.cleanarchitecturemvvm.data.remote.dto.Coin
import com.example.cleanarchitecturemvvm.data.remote.dto.toCoin
import com.example.cleanarchitecturemvvm.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(private val repository: CoinRepository) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow{
        try {
            emit(Resource.Loading<List<Coin>>())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success<List<Coin>>(coins))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "An Exception error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Coin>>("Could not reach server, please check the internet connection"))
        }
    }
}