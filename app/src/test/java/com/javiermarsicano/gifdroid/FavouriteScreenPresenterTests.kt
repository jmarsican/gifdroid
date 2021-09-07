package com.javiermarsicano.gifdroid

import com.javiermarsicano.gifdroid.data.model.Favourite
import com.javiermarsicano.gifdroid.data.persistence.entity.ImageEntity
import com.javiermarsicano.gifdroid.data.repository.FavouritesRepository
import com.javiermarsicano.gifdroid.rules.RxSchedulersOverrideRule
import com.javiermarsicano.gifdroid.ui.favourite.FavouriteScreenContract
import com.javiermarsicano.gifdroid.ui.favourite.FavouriteScreenPresenter
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when` as whenever

@RunWith(MockitoJUnitRunner::class)
class FavouriteScreenPresenterTests {

    @get:Rule
    val rxSchedulersRule = RxSchedulersOverrideRule()

    private lateinit var presenter: FavouriteScreenPresenter
    @Mock
    private lateinit var view: FavouriteScreenContract.View
    @Mock
    private lateinit var repository: FavouritesRepository

    @Before
    fun setup() {
        presenter = FavouriteScreenPresenter(repository)
        presenter.onBindView(view)
    }

    @Test
    fun `test get favourites successful`() {
        val results = listOf(Favourite("VkUdMsK42kNgrPWuHd", "https://giphy.com/gifs/MickeyMouse-VkUdMsK42kNgrPWuHd"))
        whenever(repository.loadFavourites()).thenReturn(
            Single.just(results)
        )

        presenter.getFavourites()

        verify(view).showFavourites(results)
    }
}