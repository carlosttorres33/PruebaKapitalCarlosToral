package com.carlostorres.pruebakapitalcarlostoral.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
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
    }

    private fun initRecyclerView() {
        allCardListAdapter = CardListAdapter()
        binding.rvAllCards.adapter = allCardListAdapter
        allCardListAdapter.setOnItemClickListener {
            // Handle item click
            Toast.makeText(this, "Item $it clicked", Toast.LENGTH_SHORT).show()
        }

        favoriteCardListAdapter = CardListAdapter()
        binding.rvFavoriteCards.adapter = favoriteCardListAdapter
        favoriteCardListAdapter.setOnItemClickListener {
            // Handle item click
            Toast.makeText(this, "Item $it clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->

                if (state.isLoading){
                    binding.pb.visibility = View.VISIBLE
                    binding.tvAllCards.visibility = View.GONE
                    binding.tvFavoriteCards.visibility = View.GONE
                    binding.rvAllCards.visibility = View.GONE
                    binding.tvNoFavoriteCards.visibility = View.GONE
                }else{
                    binding.pb.visibility = View.GONE
                    binding.tvAllCards.visibility = View.VISIBLE
                    binding.tvFavoriteCards.visibility = View.VISIBLE
                    binding.rvAllCards.visibility = View.VISIBLE
                    binding.tvNoFavoriteCards.visibility = View.VISIBLE
                }

                if (state.cards.isNotEmpty()) {
                    allCardListAdapter.submitList(state.cards)
                }
                if (state.favoriteCards.isNotEmpty()) {
                    favoriteCardListAdapter.submitList(state.favoriteCards)
                    binding.tvNoFavoriteCards.visibility = View.GONE
                }else{
                    binding.tvNoFavoriteCards.visibility = View.VISIBLE
                }
            }
        }
    }


}