package com.aad.composefinalproject.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aad.composefinalproject.ui.theme.ComposeFinalProjectTheme

/****************************************************
 * Created by Indra Muliana
 * On Sunday, 20/11/2022 21.52
 * Email: indra.ndra26@gmail.com
 * Github: https://github.com/indra-yana
 ****************************************************/

@Composable
fun SectionText(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
    fontStyle: FontStyle = FontStyle.Normal,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.h5.copy(
            fontWeight = fontWeight,
            fontStyle = fontStyle
        ),
        modifier = modifier.padding(
            horizontal = 8.dp,
            vertical = 2.dp,
        )
    )
}

@Preview(showBackground = true)
@Composable
fun SectionTextPreview() {
    ComposeFinalProjectTheme {
        SectionText(
            text = "Test Title",
        )
    }
}