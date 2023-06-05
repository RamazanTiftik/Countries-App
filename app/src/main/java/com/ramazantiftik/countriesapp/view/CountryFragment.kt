package com.ramazantiftik.countriesapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.ramazantiftik.countriesapp.databinding.FragmentCountryBinding
import com.ramazantiftik.countriesapp.model.Country
import com.ramazantiftik.countriesapp.util.downloadFromUrl
import com.ramazantiftik.countriesapp.util.placeHolderProgressBar
import com.ramazantiftik.countriesapp.viewmodel.CountryViewModel
import com.ramazantiftik.countriesapp.viewmodel.FeedViewModel


class CountryFragment : Fragment() {

    private var countryUuid=0

    private lateinit var viewModel: CountryViewModel

    //viewBinding
    private var _binding: FragmentCountryBinding?=null
    private val binding get() =_binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //viewBinding
        _binding= FragmentCountryBinding.inflate(inflater,container,false)
        val view=binding.root

        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryUuid=CountryFragmentArgs.fromBundle(it).countryID
        }

        viewModel=ViewModelProvider(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom(countryUuid)

        observeLiveData()

    }

    private fun observeLiveData(){

        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {

                //country details screen data
                binding.countryName.text=it.countryName
                binding.countryCapital.text=it.countryCapital
                binding.countryRegion.text=it.countryRegion
                binding.countryCurrency.text=it.countryCurrency
                binding.countryLanguage.text=it.countryLanguage

                context?.let {context ->
                    binding.countryImage.downloadFromUrl(it.imageUrl, placeHolderProgressBar(context))
                }

            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}