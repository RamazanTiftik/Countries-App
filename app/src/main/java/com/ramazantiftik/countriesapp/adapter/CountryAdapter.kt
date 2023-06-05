package com.ramazantiftik.countriesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ramazantiftik.countriesapp.databinding.RecyclerRowBinding
import com.ramazantiftik.countriesapp.model.Country
import com.ramazantiftik.countriesapp.util.downloadFromUrl
import com.ramazantiftik.countriesapp.util.placeHolderProgressBar
import com.ramazantiftik.countriesapp.view.FeedFragmentDirections

class CountryAdapter (val countryList: ArrayList<Country>) : RecyclerView.Adapter<CountryAdapter.CountryHolder>() {

    class CountryHolder (val recyclerRowBinding: RecyclerRowBinding) : RecyclerView.ViewHolder (recyclerRowBinding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val recyclerRowBinding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CountryHolder(recyclerRowBinding)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.recyclerRowBinding.countryName.setText(countryList.get(position).countryName)
        holder.recyclerRowBinding.countryRegion.setText(countryList.get(position).countryRegion)
        holder.recyclerRowBinding.imageView.downloadFromUrl(countryList.get(position).imageUrl,
            placeHolderProgressBar(holder.recyclerRowBinding.root.context)
        )

        //click
        holder.itemView.setOnClickListener {
            val action=FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }

    }

    fun updateCountryList(newCountryList: List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

}