package com.example.marvelheroes.comiclist.models

import java.io.Serializable

data class ComicPrice(
    val type: String,
    val price: Float
) : Serializable
