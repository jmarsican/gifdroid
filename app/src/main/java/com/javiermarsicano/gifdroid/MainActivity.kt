package com.javiermarsicano.gifdroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.javiermarsicano.gifdroid.databinding.ActivityMainBinding
import com.javiermarsicano.gifdroid.ui.SectionsPagerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = SectionsPagerAdapter(this, supportFragmentManager)
        binding.tabs.setupWithViewPager(binding.viewPager)
    }
}