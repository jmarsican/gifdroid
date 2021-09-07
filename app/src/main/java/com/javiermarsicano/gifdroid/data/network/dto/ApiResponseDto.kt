package com.javiermarsicano.gifdroid.data.network.dto

import com.google.gson.annotations.SerializedName

data class ApiResponseDto (
    @SerializedName("data")
    val data: List<ContentDto>
)