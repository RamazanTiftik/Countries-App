package com.ramazantiftik.countriesapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ramazantiftik.countriesapp.R
import com.ramazantiftik.countriesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}