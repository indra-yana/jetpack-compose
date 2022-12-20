package com.aad.composefinalproject.ui.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.aad.composefinalproject.MovieViewModel
import com.aad.composefinalproject.R
import com.aad.composefinalproject.ViewModelFactory
import com.aad.composefinalproject.repository.MovieRepository
import com.aad.composefinalproject.ui.component.SectionText
import com.aad.composefinalproject.ui.component.SectionTitle
import com.aad.composefinalproject.ui.theme.ComposeFinalProjectTheme

/****************************************************
 * Created by Indra Muliana
 * On Tuesday, 13/12/2022 19.35
 * Email: indra.ndra26@gmail.com
 * Github: https://github.com/indra-yana
 ****************************************************/

@Composable
fun DetailMovieScreen(
    modifier: Modifier = Modifier,
    movieId: Int,
    viewModel: MovieViewModel,
    navigateBack: () -> Unit,
) {

    val context = LocalContext.current
    val movie by viewModel.movie.collectAsState()
    viewModel.showMoviesById(movieId, context)

    Column(modifier = modifier.fillMaxHeight()) {
        SectionTitle(
            title = stringResource(R.string.title_movie_detail),
            navigateBack = { navigateBack() }
        )

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = movie.imageThumbnailPath,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.BottomCenter,
                    modifier = Modifier
                        .padding(8.dp)
                        .width(240.dp)
                        .height(350.dp)
                        .shadow(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .clip(RoundedCornerShape(20.dp))
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            SectionText(stringResource(R.string.movie_title, movie.name), fontWeight = FontWeight.Bold)
            SectionText(stringResource(R.string.movie_date, movie.startDate ?: "N/A", movie.endDate ?: "N/A"))
            SectionText(stringResource(R.string.movie_country, movie.country))
            SectionText(stringResource(R.string.movie_status, movie.status))
            SectionText(stringResource(R.string.movie_network, movie.network))
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailMoviePreview() {
    val context = LocalContext.current

    ComposeFinalProjectTheme {
        DetailMovieScreen(
            movieId = 100,
            viewModel = viewModel(factory = ViewModelFactory(MovieRepository(), context)),
            navigateBack = {},
        )
    }
}