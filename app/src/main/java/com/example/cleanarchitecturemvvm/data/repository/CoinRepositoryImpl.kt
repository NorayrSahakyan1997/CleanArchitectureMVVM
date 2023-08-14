package com.example.cleanarchitecturemvvm.data.repository

import com.example.cleanarchitecturemvvm.data.remote.CoinPaprikaApi
import com.example.cleanarchitecturemvvm.data.remote.dto.CoinDetailDto
import com.example.cleanarchitecturemvvm.data.remote.dto.CoinDto
import com.example.cleanarchitecturemvvm.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}