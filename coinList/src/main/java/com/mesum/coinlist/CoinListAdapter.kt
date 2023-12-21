package com.mesum.coinlist

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mesum.coinlist.databinding.CoinListItemBinding
import com.mesum.domain.model.Coin

class CoinListAdapter : ListAdapter<Coin, CoinListAdapter.CoinViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding = CoinListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class CoinViewHolder(private val binding: CoinListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(coin: Coin) {
            binding.tvCoinName.text = "${coin.rank}. ${coin.name} (${coin.symbol})"
            binding.tvCoinStatus.text = if (coin.isActive == true) "active" else "inactive"
            binding.tvCoinStatus.setTextColor(if (coin.isActive == true) Color.GREEN else Color.RED)
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Coin>() {
        override fun areItemsTheSame(oldItem: Coin, newItem: Coin) = oldItem.symbol == newItem.symbol
        override fun areContentsTheSame(oldItem: Coin, newItem: Coin) = oldItem == newItem
    }
}
