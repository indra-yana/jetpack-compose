package com.aad.composefinalproject.model

import android.content.Context
import com.aad.composefinalproject.utils.loadMovies

/****************************************************
 * Created by Indra Muliana
 * On Monday, 05/12/2022 19.15
 * Email: indra.ndra26@gmail.com
 * Github: https://github.com/indra-yana
 ****************************************************/

class MovieDummy(context: Context) {
    val movies = loadMovies(context)
}