package com.aad.composefinalproject.ui.pages

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aad.composefinalproject.MovieViewModel
import com.aad.composefinalproject.ViewModelFactory
import com.aad.composefinalproject.repository.MovieRepository
import com.aad.composefinalproject.ui.component.MovieCard
import com.aad.composefinalproject.ui.component.ScrollToTopButton
import com.aad.composefinalproject.ui.component.SearchBar
import com.aad.composefinalproject.ui.theme.ComposeFinalProjectTheme
import kotlinx.coroutines.launch

/****************************************************
 * Created by Indra Muliana
 * On Tuesday, 13/12/2022 19.36
 * Email: indra.ndra26@gmail.com
 * Github: https://github.com/indra-yana
 ****************************************************/

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: MovieViewModel,
    navigateToDetail: (Int) -> Unit,
) {
    val movies by viewModel.movies.collectAsState()
    val query by viewModel.query
    val context = LocalContext.current

    Box(modifier = modifier) {
        val scope = rememberCoroutineScope()
        val listState = rememberLazyListState()
        val showButton: Boolean by remember {
            derivedStateOf {
                listState.firstVisibleItemIndex > 0
            }
        }

        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            item {
                SearchBar(
                    query = query,
                    onQueryChange = {
                        viewModel.search(it, context)
                    },
                    modifier = Modifier.background(MaterialTheme.colors.primary)
                )
            }

            items(movies, key = { it.id }) { movie ->
                MovieCard(movie, onClick = {
                    navigateToDetail(movie.id)
                })
            }
        }

        AnimatedVisibility(
            visible = showButton,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
            modifier = Modifier
                .padding(bottom = 30.dp, end = 16.dp)
                .align(Alignment.BottomEnd)
        ) {
            ScrollToTopButton(
                onClick = {
                    scope.launch {
                        listState.scrollToItem(0)
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun HomeScreenPreview() {
    val context = LocalContext.current

    ComposeFinalProjectTheme {
        HomeScreen(
            viewModel = viewModel(factory = ViewModelFactory(MovieRepository(), context)),
            navigateToDetail = {}
        )
    }
}