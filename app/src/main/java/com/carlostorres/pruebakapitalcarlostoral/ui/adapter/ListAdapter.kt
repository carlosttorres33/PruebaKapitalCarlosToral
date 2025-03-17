package com.carlostorres.pruebakapitalcarlostoral.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carlostorres.pruebakapitalcarlostoral.data.remote.model.CardInfo
import com.carlostorres.pruebakapitalcarlostoral.databinding.CardItemBinding

class CardListAdapter : ListAdapter<CardInfo, CardListAdapter.CardListViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<CardInfo>() {
        override fun areItemsTheSame(oldItem: CardInfo, newItem: CardInfo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CardInfo, newItem: CardInfo): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardListViewHolder {
        val binding = CardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardListViewHolder, position: Int) {
        val cardItem = getItem(position)
        holder.bind(cardItem)
    }

    inner class CardListViewHolder(private val binding: CardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(characterItem: CardInfo) {
            Glide.with(binding.ivCardImage.context).load(characterItem.card_images.first().image_url).into(binding.ivCardImage)
            binding.tvCardName.text = characterItem.name
        }

    }

}