package com.aad.composefinalproject.repository

import android.content.Context
import com.aad.composefinalproject.model.Movie
import com.aad.composefinalproject.model.MovieDummy

/****************************************************
 * Created by Indra Muliana
 * On Monday, 12/12/2022 19.59
 * Email: indra.ndra26@gmail.com
 * Github: https://github.com/indra-yana
 ****************************************************/

class MovieRepository {
    fun getHMovies(context: Context): List<Movie> {
        return MovieDummy(context).movies
    }

    fun searchMovies(query: String, context: Context): List<Movie> {
        return MovieDummy(context).movies.filter {
            it.name.contains(query, true)
        }
    }

    fun showMoviesById(id: Int, context: Context): Movie {
        return MovieDummy(context).movies.filter {
            it.id == id
        }[0]
    }

}
