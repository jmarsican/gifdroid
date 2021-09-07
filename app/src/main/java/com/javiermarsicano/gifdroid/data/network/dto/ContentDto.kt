package com.javiermarsicano.gifdroid.data.network.dto

import com.google.gson.annotations.SerializedName

data class ContentDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val images: ImagesDto,
    @SerializedName("title")
    val title: String,
    @SerializedName("slug")
    val slug: String
)
