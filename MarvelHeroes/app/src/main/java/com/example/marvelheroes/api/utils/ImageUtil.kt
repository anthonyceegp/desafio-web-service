package com.example.marvelheroes.api.utils

import com.example.marvelheroes.comiclist.models.Image

object ImageUtil {
    fun getImagePath(image: Image, resolution: String = FULL_SIZE): String {
        var path = getHttpsPath(image.path)
        val extension = image.extension

        path = if (resolution == FULL_SIZE) {
            "$path.$extension"
        } else {
            "$path/$resolution.$extension"
        }

        return path
    }

    private fun getHttpsPath(path: String): String {
        return path.replace("http://", "https://")
    }

    const val PORTRAIT_UNCANNY = "portrait_uncanny"
    const val LANDSCAPE_INCREDIBLE = "landscape_incredible"
    const val FULL_SIZE = ""
}