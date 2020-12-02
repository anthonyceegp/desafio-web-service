package com.example.marvelheroes.comiclist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.marvelheroes.comiclist.repository.ComicRepository
import kotlinx.coroutines.Dispatchers

@Suppress("UNCHECKED_CAST")
class ComicViewModel(
    private val repository: ComicRepository
) : ViewModel() {

    fun getComics(offset: Int, limit: Int) = liveData(Dispatchers.IO) {
        val response = repository.getComics(offset, limit)

        emit(response)
    }

    class ComicViewModelFactory(
        private val repository: ComicRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ComicViewModel(repository) as T
        }
    }
}