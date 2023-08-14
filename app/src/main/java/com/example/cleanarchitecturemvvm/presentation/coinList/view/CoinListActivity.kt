package com.example.cleanarchitecturemvvm.presentation.coinList.view
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cleanarchitecturemvvm.presentation.coinList.CoinListState
import com.example.cleanarchitecturemvvm.presentation.coinList.CoinListViewModel
import com.example.cleanarchitecuremvvm.R
import com.example.cleanarchitecuremvvm.databinding.ActivityCoinListBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CoinListActivity : AppCompatActivity() {
    private lateinit var mBinding:ActivityCoinListBinding
    private val viewModel: CoinListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding=DataBindingUtil.setContentView(this,R.layout.activity_coin_list)
        mBinding.activity=this

        viewModel.getCoins()
        viewModel.state.observe(this) { coinListState ->
            updateUI(coinListState)
        }
    }
    private fun updateUI(state: CoinListState) {
        if (state.isLoading) {
            // Show loading UI
        } else if (state.error.isNotEmpty()) {
            // Show error UI with state.error
        } else {
            val text = state.coins[0].name
            mBinding.crypto.text = text
        }
    }
}