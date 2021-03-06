package com.javiermarsicano.gifdroid.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import com.javiermarsicano.gifdroid.GifDroidApplication
import com.javiermarsicano.gifdroid.R
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
        resultsAdapter = ResultsAdapter {
            getPresenter().setImageFavourite(it)
        }
        viewBinding.resultsList.adapter = resultsAdapter
        viewBinding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                getPresenter().searchImages(query.orEmpty())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //Do nothing
                return true
            }

        })
        getPresenter().getTrendingImages()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as GifDroidApplication).component.inject(this)
    }

    override fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }

    override fun clearResults() {
        resultsAdapter.clear()
    }

    override fun addResults(results: List<Content>) {
        resultsAdapter.addItems(results)
    }

    override fun updateFavourite(image: Content) {
        resultsAdapter.updateItem(image)
    }

    override fun showLoading() {
        viewBinding.loadingStatus.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        viewBinding.loadingStatus.visibility = View.GONE
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}