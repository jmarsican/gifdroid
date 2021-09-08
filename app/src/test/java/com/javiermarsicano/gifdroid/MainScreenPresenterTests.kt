package com.javiermarsicano.gifdroid

import com.javiermarsicano.gifdroid.data.model.Content
import com.javiermarsicano.gifdroid.data.model.Favourite
import com.javiermarsicano.gifdroid.data.model.ImageSpecs
import com.javiermarsicano.gifdroid.data.model.Images
import com.javiermarsicano.gifdroid.data.repository.FavouritesRepository
import com.javiermarsicano.gifdroid.data.repository.ImagesSearchRepository
import com.javiermarsicano.gifdroid.data.repository.TrendingRepository
import com.javiermarsicano.gifdroid.rules.RxSchedulersOverrideRule
import com.javiermarsicano.gifdroid.ui.main.MainScreenContract
import com.javiermarsicano.gifdroid.ui.main.MainScreenPresenter
import com.nhaarman.mockitokotlin2.argumentCaptor
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when` as whenever

@RunWith(MockitoJUnitRunner::class)
class MainScreenPresenterTests {

    @get:Rule
    val rxSchedulersRule = RxSchedulersOverrideRule()

    private lateinit var presenter: MainScreenPresenter

    @Mock
    private lateinit var view: MainScreenContract.View
    @Mock
    private lateinit var repository: TrendingRepository
    @Mock
    private lateinit var favouritesRepository: FavouritesRepository
    @Mock
    private lateinit var searchRepository: ImagesSearchRepository

    private val images = Images(ImageSpecs("https://giphy.com/gifs/MickeyMouse-VkUdMsK42kNgrPWuHd", 1024))
    private val content = Content("VkUdMsK42kNgrPWuHd", images, "Dancing dogs", "MickeyMouse-VkUdMsK42kNgrPWuHd")

    @Before
    fun setup() {
        presenter = MainScreenPresenter(repository, favouritesRepository, searchRepository)
        presenter.onBindView(view)
    }

    @Test
    fun `fetch trending successful`() {
        val imagesList = listOf(content)
        whenever(repository.getTrendingContent()).thenReturn(
            Single.just(imagesList)
        )

        presenter.getTrendingImages()

        verify(view).clearResults()
        verify(view).addResults(imagesList)
    }

    @Test
    fun `set image as favourite`() {
        presenter.setImageFavourite(content)

        val captor = argumentCaptor<Favourite>()
        verify(favouritesRepository).saveFavourite(captor.capture())
        assertEquals(captor.firstValue.id, content.id)
    }

    @Test
    fun `search images test`() {
        val imagesList = listOf(content)
        val query = "dogs"
        whenever(searchRepository.search(query))
            .thenReturn(Single.just(
                imagesList
            ))

        presenter.searchImages(query)

        verify(view).addResults(imagesList)
    }

}