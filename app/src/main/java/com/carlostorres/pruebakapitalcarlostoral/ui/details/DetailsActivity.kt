package com.carlostorres.pruebakapitalcarlostoral.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.carlostorres.pruebakapitalcarlostoral.R
import com.carlostorres.pruebakapitalcarlostoral.databinding.ActivityDetailsBinding
import com.carlostorres.pruebakapitalcarlostoral.presentation.details.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    companion object {
        fun create(context: Context) = Intent(context, DetailsActivity::class.java)
    }

    private lateinit var binding: ActivityDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()

    private val cardID: Int by lazy {
        intent.getIntExtra("cardID", 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (cardID != 0) {
            viewModel.getCardById(cardID)
        }

        initUI()

        initListeners()

    }

    private fun initListeners() {
        binding.ibFavorite.setOnClickListener {
            viewModel.updateFavoriteCard()
        }
        binding.ibBack.setOnClickListener {
            finish()
        }
    }

    private fun initUI() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                if (state.isLoading) {
                    binding.pb.visibility = View.VISIBLE
                } else {
                    binding.pb.visibility = View.GONE
                    if (state.card != null) {
                        binding.tvDetailsCardName.text = state.card.name
                        Glide.with(this@DetailsActivity)
                            .load(state.card.cardUrl)
                            .placeholder(R.drawable.background)
                            .into(binding.ivDetailCardImage)
                        binding.tvType.text = state.card.type
                        binding.tvDescription.text = state.card.desc
                        binding.ibFavorite.isSelected = state.card.isFavorite
                    }
                }
                binding.ibFavorite.isSelected = state.card?.isFavorite ?: false
            }
        }
    }
}