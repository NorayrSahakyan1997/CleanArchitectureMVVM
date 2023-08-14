package com.example.cleanarchitecturemvvm.presentation.coinList
import com.example.cleanarchitecturemvvm.data.remote.dto.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
