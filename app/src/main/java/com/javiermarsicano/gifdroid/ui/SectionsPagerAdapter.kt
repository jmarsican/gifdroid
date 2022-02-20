package com.javiermarsicano.gifdroid.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.javiermarsicano.gifdroid.R
import com.javiermarsicano.gifdroid.ui.favourite.FavouriteFragment
import com.javiermarsicano.gifdroid.ui.main.MainFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2
)

class SectionsPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MainFragment.newInstance()
            1 -> FavouriteFragment.newInstance()
            else -> MainFragment.newInstance()
        }
    }

    override fun getItemCount() = TAB_TITLES.size
}