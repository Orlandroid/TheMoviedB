package com.example.themoviedb.presentacion.util

class ImageUtil {

    companion object {
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"
        const val IMAGE_W92 = "w92"
        const val IMAGE_154 = "w154"
        const val IMAGE_185 = "w185"
        const val IMAGE_342 = "w342"
        const val IMAGE_500 = "w500"
        const val IMAGE_780 = "w780"
        const val IMAGE_ORIGINAL = "original"
    }

    private val posterSizes = hashMapOf<String, String>()

    fun setposterSizes() {
        posterSizes[IMAGE_W92] = IMAGE_W92
        posterSizes[IMAGE_154] = IMAGE_154
        posterSizes[IMAGE_185] = IMAGE_185
        posterSizes[IMAGE_342] = IMAGE_342
        posterSizes[IMAGE_500] = IMAGE_500
        posterSizes[IMAGE_780] = IMAGE_780
        posterSizes[IMAGE_ORIGINAL] = IMAGE_ORIGINAL
    }

    fun getBackgroundSize(key: String): String? {
        return posterSizes[key]
    }

    fun getBaseUrlImagePoster(posteSize: String = IMAGE_500, urlImage: String): String {
        return "$IMAGE_BASE_URL$posteSize$urlImage"
    }

}