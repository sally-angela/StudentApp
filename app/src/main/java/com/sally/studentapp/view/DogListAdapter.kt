package com.sally.studentapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sally.studentapp.databinding.DogListItemBinding
import com.sally.studentapp.model.Dog
import com.squareup.picasso.Picasso

class DogListAdapter(val dogList:ArrayList<Dog>)
    : RecyclerView.Adapter<DogListAdapter.DogViewHolder>() {
    class DogViewHolder(var binding: DogListItemBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val binding = DogListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DogListAdapter.DogViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dogList.size
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.binding.txtDogId.text = dogList[position].id.toString()
        holder.binding.txtDogBreed.text = dogList[position].breed
        holder.binding.txtDogSize.text = "Size: " + dogList[position].size
        holder.binding.txtDogColors.text = "Colors: " + dogList[position].colors?.joinToString(", ")
        holder.binding.txtFriendly.text = "Friendly: " + dogList[position].characteristics?.friendly.toString()
        holder.binding.txtIntelligent.text = "Intelligent: " + dogList[position].characteristics?.intelligent.toString()
        holder.binding.txtLoyal.text = "Loyal: " + dogList[position].characteristics?.loyal.toString()


        var url = dogList[position].images
        val builder = Picasso.Builder(holder.binding.root.context)
        builder.listener { picasso, uri, exception ->
            exception.printStackTrace() }
        builder.build().load(url).into(holder.binding.imgDog)
    }

    fun updateDogList(newDogList: ArrayList<Dog>) {
        dogList.clear()
        dogList.addAll(newDogList)
        notifyDataSetChanged()
    }
}