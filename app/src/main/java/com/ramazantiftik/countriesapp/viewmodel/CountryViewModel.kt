package com.ramazantiftik.countriesapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ramazantiftik.countriesapp.model.Country
import com.ramazantiftik.countriesapp.service.CountryDatabase
import kotlinx.coroutines.launch

class CountryViewModel(application: Application) : BaseViewModel(application) {

    val countryLiveData=MutableLiveData<Country>()

    fun getDataFromRoom(uuid: Int){
        launch {

            val dao=CountryDatabase(getApplication()).countryDao()
            val country=dao.getCountry(uuid)
            countryLiveData.value=country

        }
    }

}