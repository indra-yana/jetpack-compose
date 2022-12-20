package com.aad.composefinalproject

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aad.composefinalproject.model.Movie
import com.aad.composefinalproject.repository.MovieRepository
import com.aad.composefinalproject.utils.dummyMovie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/****************************************************
 * Created by Indra Muliana
 * On Monday, 12/12/2022 20.02
 * Email: indra.ndra26@gmail.com
 * Github: https://github.com/indra-yana
 ****************************************************/

class MovieViewModel(private val repository: MovieRepository, context: Context) : ViewModel() {
    private val _movies = MutableStateFlow(
        repository.getHMovies(context)
    )
    val movies: StateFlow<List<Movie>> get() = _movies

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    private val _movie = MutableStateFlow(dummyMovie())
    val movie: StateFlow<Movie> get() = _movie

    fun search(newQuery: String, context: Context) {
        _query.value = newQuery
        _movies.value = repository.searchMovies(_query.value, context).sortedBy { it.name }
    }

    fun showMoviesById(id: Int, context: Context) {
        _movie.value = repository.showMoviesById(id, context)
    }
}

class ViewModelFactory(private val repository: MovieRepository, private val context: Context) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(repository, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}