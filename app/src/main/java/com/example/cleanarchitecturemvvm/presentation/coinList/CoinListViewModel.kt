package com.example.cleanarchitecturemvvm.presentation.coinList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecturemvvm.common.Resource
import com.example.cleanarchitecturemvvm.domain.use_case.getCoins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) :
    ViewModel() {


    private val _state = MutableLiveData(CoinListState())
    val state: LiveData<CoinListState> = _state

    init {
        getCoins()
    }

     fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CoinListState(coins = result.data ?: emptyList())

                    println(result.data!!.size)
                }
                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                    println("Is Loading")
                }
                is Resource.Error -> {
                    _state.value =
                        CoinListState(error = result.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }

}