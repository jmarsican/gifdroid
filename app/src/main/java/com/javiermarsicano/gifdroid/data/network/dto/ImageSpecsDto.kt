package com.javiermarsicano.gifdroid.data.network.dto

import com.google.gson.annotations.SerializedName

data class ImageSpecsDto(
    @SerializedName("url")
    val url: String,
    @SerializedName("size")
    val size: Int?
)
