package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ItemUserBinding

class AdapterUser(
    private val onClick: (User) -> Unit
):RecyclerView.Adapter<AdapterUser.ViewHolder>() {
    var list = mutableListOf<User>()
    lateinit var context: Context
    inner class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.root.setOnClickListener {
                onClick.invoke(user)
            }
            Glide.with(context)
                .load(user.avatar)
                .into(binding.imgAvatar)
            binding.tvName.text =user.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemUserBinding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        context = parent.context
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

}