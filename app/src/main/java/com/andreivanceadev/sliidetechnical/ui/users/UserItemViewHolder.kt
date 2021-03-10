package com.andreivanceadev.sliidetechnical.ui.users

import androidx.recyclerview.widget.RecyclerView
import com.andreivanceadev.sliidetechnical.databinding.RowUserBinding
import com.andreivanceadev.sliidetechnical.models.UserItem

class UserItemViewHolder(private val binding: RowUserBinding) :
    RecyclerView.ViewHolder(binding.root) {


    fun bind(user: UserItem, onItemSelected: (user: UserItem) -> Unit?) {
        binding.tvName.text = user.name
        binding.tvMail.text = user.email
        binding.tvCreatedAt.text = user.createdAt

        itemView.setOnLongClickListener {
            onItemSelected(user)
            return@setOnLongClickListener true
        }
    }

}
