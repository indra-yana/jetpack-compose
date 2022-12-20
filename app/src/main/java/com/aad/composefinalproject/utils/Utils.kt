package com.aad.composefinalproject.utils

import android.content.Context
import android.util.Log
import com.aad.composefinalproject.model.Movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/****************************************************
 * Created by Indra Muliana
 * On Sunday, 04/12/2022 10.44
 * Email: indra.ndra26@gmail.com
 * Github: https://github.com/indra-yana
 ****************************************************/

const val TAG = "UTILS"

fun loadMovies(context: Context): List<Movie> {
    return try {
        val jsonString: String = context.resources.assets.open("movies.json").bufferedReader().use {
            it.readText()
        }

        Log.e(TAG, "loadMovies: $jsonString")

        val type = object : TypeToken<List<Movie>>() {}.type
        Gson().fromJson(jsonString, type)
    } catch (ex: Exception) {
        Log.e(TAG, "loadMovies: " + ex.stackTraceToString())
        emptyList()
    }
}

fun dummyMovie(): Movie {
    return Movie(
        id = 0,
        country = "",
        startDate = "",
        endDate = "",
        imageThumbnailPath = "",
        name = "",
        network = "",
        permalink = "",
        status = ""
    )
}