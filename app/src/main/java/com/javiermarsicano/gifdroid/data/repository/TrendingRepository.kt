package com.javiermarsicano.gifdroid.data.repository

import com.javiermarsicano.gifdroid.data.model.Content
import io.reactivex.Single

interface TrendingRepository {
    fun getTrendingContent(): Single<List<Content>>
}