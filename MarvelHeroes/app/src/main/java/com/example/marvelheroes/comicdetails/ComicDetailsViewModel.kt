package com.example.marvelheroes.comicdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marvelheroes.comiclist.models.Comic

@Suppress("UNCHECKED_CAST")
class ComicDetailsViewModel(
    val comic: Comic
) : ViewModel() {

    class ComicDetailsViewModelFactory(
        private val comic: Comic
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ComicDetailsViewModel(comic) as T
        }
    }
}