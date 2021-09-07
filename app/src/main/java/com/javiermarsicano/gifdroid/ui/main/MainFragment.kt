package com.javiermarsicano.gifdroid.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.javiermarsicano.gifdroid.GifDroidApplication
import com.javiermarsicano.gifdroid.base.BaseMVPFragment
import com.javiermarsicano.gifdroid.data.model.Content
import com.javiermarsicano.gifdroid.databinding.FragmentMainBinding
import timber.log.Timber
import javax.inject.Inject

class MainFragment : BaseMVPFragment<FragmentMainBinding, MainScreenContract.View, MainScreenContract.Presenter>(), MainScreenContract.View {

    @Inject
    lateinit var mPresenter: MainScreenContract.Presenter

    override fun getPresenter(): MainScreenContract.Presenter = mPresenter

    private lateinit var resultsAdapter: ResultsAdapter

    override fun bindViews() {
        super.bindViews()
        resultsAdapter = ResultsAdapter {
            Timber.d("CLICK!!")
        }
        viewBinding.resultsList.adapter = resultsAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as GifDroidApplication).component.inject(this)
        getPresenter().getTrendingImages()

    }

    override fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }

    override fun clearResults() {

    }

    override fun addTrendingResults(results: List<Content>) {
        resultsAdapter.addItems(results)
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}