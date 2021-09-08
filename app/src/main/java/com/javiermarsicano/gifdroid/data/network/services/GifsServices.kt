package com.javiermarsicano.gifdroid.data.network.services

import com.javiermarsicano.gifdroid.BuildConfig.GIFS_SERVICES_API_KEY
import com.javiermarsicano.gifdroid.data.network.dto.ApiResponseDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

private const val VERSION = "v1"

interface GifsServices {

    @GET("/$VERSION/gifs/trending")
    fun getTrending(@Query("api_key") key: String = GIFS_SERVICES_API_KEY): Single<ApiResponseDto>

    @GET("/$VERSION/gifs/search")
    fun search(@Query("api_key") key: String = GIFS_SERVICES_API_KEY, @Query("q") query: String): Single<ApiResponseDto>
}