package com.javiermarsicano.gifdroid.data.persistence.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite")
data class ImageEntity(
    @PrimaryKey
    val id: String,
    val url: String
)
