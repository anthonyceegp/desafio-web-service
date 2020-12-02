package com.example.marvelheroes.comiclist.models

import java.io.Serializable

data class Image(
    val path: String,
    val extension: String
) : Serializable
