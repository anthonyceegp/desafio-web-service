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

    const val PORTRAIT_SMALL = "portrait_small"
    const val PORTRAIT_MEDIUM = "portrait_medium"
    const val PORTRAIT_XLARGE = "portrait_xlarge"
    const val PORTRAIT_FANTASTIC = "portrait_fantastic"
    const val PORTRAIT_UNCANNY = "portrait_uncanny"
    const val PORTRAIT_INCREDIBLE = "portrait_incredible"
    const val STANDARD_SMALL = "standard_small"
    const val STANDARD_MEDIUM = "standard_medium"
    const val STANDARD_LARGE = "standard_large"
    const val STANDARD_XLARGE = "standard_xlarge"
    const val STANDARD_FANTASTIC = "standard_fantastic"
    const val STANDARD_AMAZING = "standard_amazing"
    const val LANDSCAPE_SMALL = "landscape_small"
    const val LANDSCAPE_MEDIUM = "landscape_medium"
    const val LANDSCAPE_LARGE = "landscape_large"
    const val LANDSCAPE_XLARGE = "landscape_xlarge"
    const val LANDSCAPE_AMAZING = "landscape_amazing"
    const val LANDSCAPE_INCREDIBLE = "landscape_incredible"
    const val DETAIL = "detail"
    const val FULL_SIZE = ""
}