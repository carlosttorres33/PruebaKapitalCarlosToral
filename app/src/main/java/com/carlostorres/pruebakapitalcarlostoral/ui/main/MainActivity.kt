package com.carlostorres.pruebakapitalcarlostoral.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.carlostorres.pruebakapitalcarlostoral.databinding.ActivityMainBinding
import com.carlostorres.pruebakapitalcarlostoral.presentation.main.MainViewModel
import com.carlostorres.pruebakapitalcarlostoral.ui.adapter.CardListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var allCardListAdapter: CardListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()

    }

    private fun initUI() {
        initUIState()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        allCardListAdapter = CardListAdapter()
        binding.rvAllCards.adapter = allCardListAdapter
    }

    private fun initUIState() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                binding.pb.isVisible = state.isLoading
                if (state.cards.isNotEmpty()) {
                    allCardListAdapter.submitList(state.cards)
                }
            }
        }
    }


}