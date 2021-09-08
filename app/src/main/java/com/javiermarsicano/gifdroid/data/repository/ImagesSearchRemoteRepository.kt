package com.javiermarsicano.gifdroid.data.repository

import com.javiermarsicano.gifdroid.data.model.Content
import io.reactivex.Single

class ImagesSearchRemoteRepository: ImagesSearchRepository {
    override fun search(query: String): Single<List<Content>> {
        TODO("Not yet implemented")
    }
}