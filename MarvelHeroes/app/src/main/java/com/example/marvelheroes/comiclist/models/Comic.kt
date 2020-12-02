package com.example.marvelheroes.comiclist.models

import java.io.Serializable

data class Comic(
    val id: Int,
    val title: String,
    val issueNumber: Double,
    val description: String?,
    val pageCount: Int,
    val dates: List<ComicDate>?,
    val prices: List<ComicPrice>?,
    val thumbnail: Image,
    val images: List<Image>
) : Serializable