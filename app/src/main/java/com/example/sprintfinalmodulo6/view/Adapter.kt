package com.example.sprintfinalmodulo6.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sprintfinalmodulo6.databinding.ItemGridBinding
import com.example.sprintfinalmodulo6.model.local.entities.PhonesEntity

class Adapter: RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var listPhones = listOf<PhonesEntity>()
    private val selectedPhone = MutableLiveData<PhonesEntity>()

    fun update(list: List<PhonesEntity>){
        listPhones = list
        notifyDataSetChanged()
    }

    fun selectedPhone():
            LiveData<PhonesEntity> = selectedPhone

    inner class ViewHolder(private val binding: ItemGridBinding):
    RecyclerView.ViewHolder(binding.root), View.OnClickListener{

        fun bind(phone:PhonesEntity){
            Glide.with(binding.img).load(phone.image).centerCrop().into(binding.img)

            binding.txtName.text = phone.name
            binding.txtPrice.text = "$${phone.price.toString()}"
            itemView.setOnClickListener (this)
        }

        override fun onClick(v: View?) {
            selectedPhone.value=listPhones[adapterPosition]
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
        return ViewHolder(ItemGridBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        val phone = listPhones[position]
        holder.bind(phone)
    }

    override fun getItemCount(): Int = listPhones.size
}