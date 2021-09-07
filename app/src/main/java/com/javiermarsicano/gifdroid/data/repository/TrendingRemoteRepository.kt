package com.javiermarsicano.gifdroid.data.repository

import com.javiermarsicano.gifdroid.data.model.Content
import io.reactivex.Single

class TrendingRemoteRepository(): BaseRemoteRepository(), TrendingRepository {
    override fun getTrendingContent(): Single<Content> {
        TODO("Not yet implemented")
    }
}