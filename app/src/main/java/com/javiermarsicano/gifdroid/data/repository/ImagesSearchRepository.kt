package com.javiermarsicano.gifdroid.data.repository

import com.javiermarsicano.gifdroid.data.model.Content
import io.reactivex.Single

interface ImagesSearchRepository {
    fun search(query: String): Single<List<Content>>
}