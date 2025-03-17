package com.carlostorres.pruebakapitalcarlostoral.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carlostorres.pruebakapitalcarlostoral.R
import com.carlostorres.pruebakapitalcarlostoral.data.local.model.CardEntity
import com.carlostorres.pruebakapitalcarlostoral.databinding.CardItemBinding

class CardListAdapter : ListAdapter<CardEntity, CardListAdapter.CardListViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<CardEntity>() {
        override fun areItemsTheSame(oldItem: CardEntity, newItem: CardEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CardEntity, newItem: CardEntity): Boolean {
            return oldItem == newItem
        }

    }

    private var onItemClickListener: ((Int) -> Unit)? = null
    fun setOnItemClickListener(listener: (Int) -> Unit) {
        this.onItemClickListener = listener
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

        fun bind(characterItem: CardEntity) {
            Glide.with(binding.ivCardImage.context)
                .load(characterItem.cardUrl)
                .placeholder(R.drawable.background)
                .into(binding.ivCardImage)
            binding.tvCardName.text = characterItem.name
            binding.ivCardImage.setOnClickListener {
                onItemClickListener?.invoke(characterItem.id)
            }
        }

    }

}