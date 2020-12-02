package com.example.marvelheroes.comiclist.repository

import com.example.marvelheroes.api.utils.NetworkUtils
import com.example.marvelworld.comiclist.repository.ComicEndpoint

class ComicRepository {
    private val comicService = NetworkUtils.getApiService(ComicEndpoint::class.java)

    suspend fun getComics(offset: Int, limit: Int) = comicService.getComics(offset, limit)
}