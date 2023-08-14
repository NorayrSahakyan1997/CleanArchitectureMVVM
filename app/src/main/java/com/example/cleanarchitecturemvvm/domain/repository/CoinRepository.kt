package com.example.cleanarchitecturemvvm.domain.repository

import com.example.cleanarchitecturemvvm.data.remote.dto.CoinDetailDto
import com.example.cleanarchitecturemvvm.data.remote.dto.CoinDto


interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId:String): CoinDetailDto

}