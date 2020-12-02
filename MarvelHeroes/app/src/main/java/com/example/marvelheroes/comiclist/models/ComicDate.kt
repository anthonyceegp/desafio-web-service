package com.example.marvelheroes.comiclist.models

import java.io.Serializable
import java.util.*

data class ComicDate (
    val type: String,
    val date: Date
) : Serializable