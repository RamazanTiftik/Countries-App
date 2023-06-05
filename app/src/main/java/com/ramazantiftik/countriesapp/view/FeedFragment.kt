package com.ramazantiftik.countriesapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ramazantiftik.countriesapp.adapter.CountryAdapter
import com.ramazantiftik.countriesapp.databinding.FragmentFeedBinding
import com.ramazantiftik.countriesapp.model.Country
import com.ramazantiftik.countriesapp.viewmodel.FeedViewModel

class FeedFragment : Fragment() {

    //viewBinding
    private var _binding: FragmentFeedBinding?=null
    private val binding get() = _binding!!

    private lateinit var viewModel: FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //viewBinding
        _binding= FragmentFeedBinding.inflate(inflater,container,false)
        val view=binding.root

        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //viewModel connect
        viewModel= ViewModelProvider(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        //recyclerView adapter
        binding.recyclerView.layoutManager=LinearLayoutManager(context)
        binding.recyclerView.adapter=countryAdapter

        observeLiveData()

        swipeRefreshData()

    }

    fun observeLiveData(){

        viewModel.countries.observe(viewLifecycleOwner, Observer { countries ->

            countries?.let {
                binding.recyclerView.visibility=View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }

        })

        viewModel.countryErrorText.observe(viewLifecycleOwner, Observer {error ->

            error?.let {
                if (it){
                    binding.countryErrorText.visibility=View.VISIBLE
                } else {
                    binding.countryErrorText.visibility=View.GONE
                }
            }

        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer {loading ->

            loading?.let {
                if (it){
                    binding.countryLoading.visibility=View.VISIBLE
                    binding.countryErrorText.visibility=View.GONE
                    binding.recyclerView.visibility=View.GONE
                } else {
                    binding.countryLoading.visibility=View.GONE
                }
            }

        })

    }

    //swipe screen
    private fun swipeRefreshData(){
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.recyclerView.visibility=View.GONE
            binding.countryErrorText.visibility=View.GONE
            binding.countryLoading.visibility=View.VISIBLE

            viewModel.refreshFromAPI()
            binding.swipeRefreshLayout.isRefreshing=false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}