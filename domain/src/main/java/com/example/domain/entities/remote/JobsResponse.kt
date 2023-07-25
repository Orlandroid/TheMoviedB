package com.example.domain.entities.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class JobsResponse(
    val department: String,
    val jobs: List<String>
) : Parcelable {


    /*
    fun getImageDepartment(): Int {
        return when (department) {
            "Visual Effects" -> {
                return R.drawable.note
            }

            "Crew" -> {
                return R.drawable.crew
            }

            "Lighting" -> {
                return R.drawable.electrician
            }

            "Camera" -> {
                return R.drawable.cameraman
            }

            "Directing" -> {
                return R.drawable.films_director
            }

            "Editing" -> {
                return R.drawable.cameraman
            }

            "Production" -> {
                return R.drawable.productor
            }

            "Costume & Make-Up" -> {
                return R.drawable.makeup_artist
            }

            "Actors" -> {
                return R.drawable.actor
            }

            "Writing" -> {
                return R.drawable.escritor
            }

            "Art" -> {
                return R.drawable.artist
            }

            "Sound" -> {
                return R.drawable.crew
            }

            else -> {
                R.drawable.note
            }
        }
    }*/


}