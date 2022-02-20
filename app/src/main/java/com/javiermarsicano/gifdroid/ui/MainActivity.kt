package com.javiermarsicano.gifdroid.ui

import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.javiermarsicano.gifdroid.base.BaseActivity
import com.javiermarsicano.gifdroid.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = SectionsPagerAdapter(this)
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, pos ->
            tab.text = "Tab $pos"
        }.attach()
    }
}