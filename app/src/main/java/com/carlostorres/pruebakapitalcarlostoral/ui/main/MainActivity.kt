package com.carlostorres.pruebakapitalcarlostoral.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.carlostorres.pruebakapitalcarlostoral.R
import com.carlostorres.pruebakapitalcarlostoral.data.local.model.CardEntity
import com.carlostorres.pruebakapitalcarlostoral.databinding.ActivityMainBinding
import com.carlostorres.pruebakapitalcarlostoral.presentation.main.MainViewModel
import com.carlostorres.pruebakapitalcarlostoral.ui.adapter.CardListAdapter
import com.carlostorres.pruebakapitalcarlostoral.ui.details.DetailsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var allCardListAdapter: CardListAdapter
    private lateinit var favoriteCardListAdapter: CardListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()

    }

    private fun initUI() {
        initUIState()
        initRecyclerView()
        initListeners()
    }

    private fun initListeners() {
        binding.btnRetry.setOnClickListener {
            viewModel.setUpCards()
        }
    }

    private fun initRecyclerView() {
        allCardListAdapter = CardListAdapter()
        binding.rvAllCards.adapter = allCardListAdapter
        allCardListAdapter.setOnItemClickListener { cardID ->
            startActivity(DetailsActivity.create(this).putExtra("cardID", cardID))
        }

        favoriteCardListAdapter = CardListAdapter()
        binding.rvFavoriteCards.adapter = favoriteCardListAdapter
        favoriteCardListAdapter.setOnItemClickListener { cardID ->
            startActivity(DetailsActivity.create(this).putExtra("cardID", cardID))
        }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->

                handleLoadingState(state.isLoading)
                handleErrorState(state.error)
                handleAllCardsState(state.cards)
                handleFavoriteCardsState(state.favoriteCards)
            }
        }
    }

    private fun handleLoadingState(isLoading: Boolean) {
        if (isLoading) {
            binding.pb.visibility = View.VISIBLE
            binding.tvNoFavoriteCards.visibility = View.GONE
            binding.tvNoFavoriteCards.text = getString(R.string.cargando_text)
            binding.tvAllCards.visibility = View.GONE
            binding.tvFavoriteCards.visibility = View.GONE
            binding.rvAllCards.visibility = View.GONE
            binding.rvFavoriteCards.visibility = View.GONE
            binding.btnRetry.visibility = View.GONE
        } else {
            binding.pb.visibility = View.GONE
            binding.btnRetry.visibility = View.GONE
            binding.tvAllCards.visibility = View.VISIBLE
            binding.tvFavoriteCards.visibility = View.VISIBLE
            binding.rvAllCards.visibility = View.VISIBLE
            binding.tvNoFavoriteCards.visibility = View.VISIBLE
            binding.tvNoFavoriteCards.text = getString(R.string.no_hay_cartas_favoritas)
            binding.rvFavoriteCards.visibility = View.VISIBLE
        }
    }

    private fun handleErrorState(error: String) {
        if (error.isNotEmpty()) {
            Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
            binding.pb.visibility = View.GONE
            binding.btnRetry.visibility = View.VISIBLE
            binding.tvNoFavoriteCards.visibility = View.VISIBLE
            binding.tvNoFavoriteCards.text = error
            binding.tvAllCards.visibility = View.GONE
            binding.tvFavoriteCards.visibility = View.GONE
            binding.rvAllCards.visibility = View.GONE
            binding.rvFavoriteCards.visibility = View.GONE
        }
    }

    private fun handleAllCardsState(cards: List<CardEntity>) {
        if (cards.isNotEmpty()) {
            allCardListAdapter.submitList(cards)
        }
    }

    private fun handleFavoriteCardsState(favoriteCards: List<CardEntity>) {
        if (favoriteCards.isNotEmpty()) {
            favoriteCardListAdapter.submitList(favoriteCards)
            binding.tvNoFavoriteCards.visibility = View.GONE
        } else {
            binding.tvNoFavoriteCards.visibility = View.VISIBLE
            binding.rvFavoriteCards.visibility = View.GONE
        }
    }


}