package com.ramazantiftik.countriesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

open class BaseViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {

    private val job= Job()

    override val coroutineContext: CoroutineContext
        get() = job+Dispatchers.Main // firstly do job, after go to main thread

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}