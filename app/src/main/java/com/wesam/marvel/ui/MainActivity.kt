package com.wesam.marvel.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wesam.marvel.R
import com.wesam.marvel.model.local.database.MarvelDatabase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MarvelDatabase.getInstance(applicationContext)
    }
}