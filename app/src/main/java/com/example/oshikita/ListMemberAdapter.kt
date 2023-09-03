package com.example.oshikita

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.oshikita.databinding.ItemRowMemberBinding

class ListMemberAdapter(private val listMember: ArrayList<Member>) : RecyclerView.Adapter<ListMemberAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemCLickCallback

    interface OnItemCLickCallback {
        fun onItemClicked(data: Member)
    }

    fun setOnItemClickCallback(callback: OnItemCLickCallback) {
        this.onItemClickCallback = callback
    }

    class ListViewHolder(val binding: ItemRowMemberBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowMemberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listMember.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val member = listMember[position]
        val (fullname, _, _, generation, jiko, _, photo) = member
        holder.binding.tvItemName.text = fullname
        holder.binding.tvItemGeneration.text = "Generasi $generation"
        holder.binding.tvItemJiko.text = jiko
        holder.binding.imgItemPhoto.setImageResource(photo)

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(member)
        }
    }
}