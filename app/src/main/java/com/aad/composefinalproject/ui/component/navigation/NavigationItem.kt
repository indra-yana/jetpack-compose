package com.aad.composefinalproject.ui.component.navigation

import androidx.compose.ui.graphics.vector.ImageVector

/****************************************************
 * Created by Indra Muliana
 * On Sunday, 27/11/2022 19.05
 * Email: indra.ndra26@gmail.com
 * Github: https://github.com/indra-yana
 ****************************************************/

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
)
