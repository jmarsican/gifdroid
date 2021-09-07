package com.javiermarsicano.gifdroid.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.javiermarsicano.gifdroid.base.BaseMVPFragment
import com.javiermarsicano.gifdroid.data.model.Content
import com.javiermarsicano.gifdroid.databinding.FragmentMainBinding
import javax.inject.Inject

class MainFragment : BaseMVPFragment<FragmentMainBinding, MainScreenContract.View, MainScreenContract.Presenter>(), MainScreenContract.View {

    @Inject
    lateinit var mPresenter: MainScreenContract.Presenter

    override fun getPresenter(): MainScreenContract.Presenter = mPresenter

    override fun bindViews() {
        super.bindViews()
    }

    override fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }

    override fun clearResults() {
        TODO("Not yet implemented")
    }

    override fun addTrendingResults(results: List<Content>) {
        TODO("Not yet implemented")
    }


    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}