package com.javiermarsicano.gifdroid.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.javiermarsicano.gifdroid.GifDroidApplication
import com.javiermarsicano.gifdroid.base.BaseMVPFragment
import com.javiermarsicano.gifdroid.data.model.Favourite
import com.javiermarsicano.gifdroid.databinding.FragmentFavouriteBinding
import javax.inject.Inject

class FavouriteFragment :
    BaseMVPFragment<FragmentFavouriteBinding, FavouriteScreenContract.View, FavouriteScreenContract.Presenter>(),
        FavouriteScreenContract.View {

    @Inject
    lateinit var mPresenter: FavouriteScreenContract.Presenter
    override fun getPresenter(): FavouriteScreenContract.Presenter = mPresenter

    private lateinit var favouritesAdapter: FavouritesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as GifDroidApplication).component.inject(this)
    }

    override fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFavouriteBinding {
        return FragmentFavouriteBinding.inflate(inflater, container, false)
    }

    override fun bindViews() {
        favouritesAdapter = FavouritesAdapter {
            getPresenter().removeFavourite(it)
        }
        viewBinding.favouritesList.adapter = favouritesAdapter
    }

    override fun onResume() {
        super.onResume()
        getPresenter().getFavourites()
    }

    override fun showFavourites(favourites: List<Favourite>) {
        favouritesAdapter.addItems(favourites)
    }

    override fun showLoading() {
        //TODO
    }

    override fun hideLoading() {
        //TODO
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavouriteFragment()
    }

}