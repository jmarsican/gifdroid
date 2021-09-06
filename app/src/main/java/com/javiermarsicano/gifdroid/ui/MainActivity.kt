package com.javiermarsicano.gifdroid.ui

import android.os.Bundle
import com.javiermarsicano.gifdroid.base.BaseActivity
import com.javiermarsicano.gifdroid.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = SectionsPagerAdapter(this, supportFragmentManager)
        binding.tabs.setupWithViewPager(binding.viewPager)
    }
}