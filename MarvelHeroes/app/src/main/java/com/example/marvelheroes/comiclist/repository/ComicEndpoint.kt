package com.example.marvelheroes.comiclist.repository

import com.example.marvelheroes.comiclist.models.Comic
import com.example.marvelheroes.api.models.DataWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicEndpoint {
    @GET("/v1/public/comics")
    suspend fun getComics(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("orderBy") orderBy: String = "-modified"
    ): DataWrapper<Comic>
}