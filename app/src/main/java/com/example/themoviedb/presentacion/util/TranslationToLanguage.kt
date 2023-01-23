package com.example.themoviedb.presentacion.util

class TranslationToLanguage {
    fun getLanguageFromTranslation(translation: String): String {
        return when(translation.substring(0,1)){
            "af"-> "Afghanistan"
            "ar"-> "Afghanistan"
            "af"-> "Afghanistan"
            "af"-> "Afghanistan"
            else -> {""}
        }
    }
}