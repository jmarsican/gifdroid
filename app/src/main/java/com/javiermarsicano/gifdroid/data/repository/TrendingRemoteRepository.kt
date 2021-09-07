package com.javiermarsicano.gifdroid.data.repository

import android.content.Context
import android.net.Uri
import com.javiermarsicano.gifdroid.data.model.Content
import io.reactivex.Single

class TrendingRemoteRepository(context: Context, baseUrl: Uri): BaseRemoteRepository(context, baseUrl), TrendingRepository {
    override fun getTrendingContent(): Single<List<Content>> {
        return servicesApi.getTrending().map { response ->
            response.data.map {
                it.toModel()
            }
        }
    }
}