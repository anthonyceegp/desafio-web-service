package com.example.marvelheroes.api.models

data class DataContainer<T>(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<T>
)