package com.javiermarsicano.gifdroid.data.repository

import android.content.Context
import android.net.Uri
import com.google.gson.Gson
import com.javiermarsicano.gifdroid.BuildConfig
import com.javiermarsicano.gifdroid.data.model.Content
import com.javiermarsicano.gifdroid.data.model.ImageSpecs
import com.javiermarsicano.gifdroid.data.model.Images
import com.javiermarsicano.gifdroid.data.network.dto.ContentDto
import com.javiermarsicano.gifdroid.data.network.dto.ImageSpecsDto
import com.javiermarsicano.gifdroid.data.network.dto.ImagesDto
import com.javiermarsicano.gifdroid.data.network.services.GifsServices
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import timber.log.Timber
import java.io.File
import java.util.concurrent.TimeUnit


private const val OK_HTTP_CACHE_NAME = "okHttp-cache"
private const val CACHE_SIZE = 10 * 1024 * 1024
private const val OKHTTP_TIMEOUT_SEC = 25L

open class BaseRemoteRepository(val context: Context, private val baseUrl: Uri) {
    val servicesApi: GifsServices

    init {
        val httpLoggingInterceptor = HttpLoggingInterceptor { message -> Timber.d(message) }
        httpLoggingInterceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }

        val okhttpBuilder = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(OKHTTP_TIMEOUT_SEC, TimeUnit.SECONDS)
            .readTimeout(OKHTTP_TIMEOUT_SEC, TimeUnit.SECONDS)
            .writeTimeout(OKHTTP_TIMEOUT_SEC, TimeUnit.SECONDS)
            .cache(Cache(File(context.cacheDir, OK_HTTP_CACHE_NAME), CACHE_SIZE.toLong()))

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl.toString())
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okhttpBuilder.build())
            .build()
        servicesApi = retrofit.create(GifsServices::class.java)
    }

    fun ContentDto.toModel() =
        Content(
            this.id,
            this.images.toModel(),
            this.title,
            this.slug
        )

    private fun ImagesDto.toModel() = Images(this.original.toModel())

    private fun ImageSpecsDto.toModel() =
        ImageSpecs(
            this.url,
            this.size
        )
}
