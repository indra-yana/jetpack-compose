package com.aad.composefinalproject.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.aad.composefinalproject.model.Movie
import com.aad.composefinalproject.ui.theme.ComposeFinalProjectTheme
import com.aad.composefinalproject.utils.dummyMovie

/****************************************************
 * Created by Indra Muliana
 * On Monday, 05/12/2022 19.33
 * Email: indra.ndra26@gmail.com
 * Github: https://github.com/indra-yana
 ****************************************************/

@Composable
fun MovieCard(
    movie: Movie,
    onClick: (movie: Movie) -> Unit
) {

    Card(
        backgroundColor = MaterialTheme.colors.secondary,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .padding(
                vertical = 8.dp,
                horizontal = 12.dp
            )
            .clickable(onClick = { onClick(movie) }),
    ) {
        Row(
            modifier = Modifier.padding(3.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = movie.imageThumbnailPath,
                contentDescription = "Movie List",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(8.dp)
                    .size(70.dp)
                    .border(1.dp, Color.DarkGray, CircleShape)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = movie.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${movie.country} | ${movie.network}",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body1.copy(
                        fontStyle = FontStyle.Italic
                    )
                )
            }
        }
    }

}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MovieCardPreview() {
    ComposeFinalProjectTheme() {
        MovieCard(
            movie = dummyMovie(),
            onClick = {}
        )
    }
}