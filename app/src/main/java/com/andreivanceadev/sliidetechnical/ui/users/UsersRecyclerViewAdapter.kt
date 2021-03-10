package com.andreivanceadev.sliidetechnical.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andreivanceadev.sliidetechnical.databinding.RowUserBinding
import com.andreivanceadev.sliidetechnical.models.UserItem

class UsersRecyclerViewAdapter : RecyclerView.Adapter<UserItemViewHolder>() {

    private var model: MutableList<UserItem> = mutableListOf()
    private var onItemSelected: (user: UserItem) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        val binding = RowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        holder.bind(model[position], onItemSelected)
    }

    override fun getItemCount(): Int = model.size

    fun setModel(users: List<UserItem>) {
        if (users.size < model.size) {
            val itemsToRemove = model.filter {
                !users.contains(it)
            }
            itemsToRemove.forEach{
                val removedIndex = model.indexOf(it)
                model.remove(it)
                notifyItemRemoved(removedIndex)
            }
        } else {
            model.clear()
            model.addAll(users)
            notifyDataSetChanged()
        }
    }

    fun setCallback(onItemSelected: (user: UserItem) -> Unit) {
        this.onItemSelected = onItemSelected
    }

}
