package com.javiermarsicano.gifdroid.data.network.dto

import com.google.gson.annotations.SerializedName

data class ImagesDto(
    @SerializedName("original")
    val original: ImageSpecsDto
)
