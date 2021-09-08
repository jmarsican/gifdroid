package com.javiermarsicano.gifdroid.data.repository

import android.content.Context
import android.net.Uri
import com.javiermarsicano.gifdroid.data.model.Content
import io.reactivex.Single

class ImagesSearchRemoteRepository(context: Context, baseUrl: Uri): BaseRemoteRepository(context, baseUrl), ImagesSearchRepository {
    override fun search(query: String): Single<List<Content>> {
        return servicesApi.search(query = query).map { response ->
            response.data.map { it.toModel() }
        }
    }
}