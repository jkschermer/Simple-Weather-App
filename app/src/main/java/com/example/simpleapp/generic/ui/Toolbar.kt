package com.example.simpleapp.generic.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.simpleapp.R
import com.example.simpleapp.SimpleAppTheme
import com.example.simpleapp.theme.Spacing.x1
import com.example.simpleapp.theme.Spacing.x2
import com.example.simpleapp.theme.Spacing.x8

@Composable
fun AppToolbar(
    @StringRes textResId: Int,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(x8)
            .fillMaxWidth(),
    ) {
        Image(
            painter = painterResource(R.drawable.globalweather),
            contentDescription = null,
            alignment = Alignment.CenterStart,
            contentScale = ContentScale.Crop,
            modifier = Modifier.padding(start = x1)
        )
        Text(
            stringResource(textResId),
            style = MaterialTheme.typography.bodyLarge.merge(TextStyle(fontWeight = FontWeight.Bold)),
            color = textColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = x2)
        )
    }
}

@Preview
@Composable
private fun PreviewToolbar() {
    SimpleAppTheme(darkTheme = true) {
        AppToolbar(
            textResId = R.string.homescreen_toolbar_title,
            textColor = MaterialTheme.colorScheme.background,
            modifier = Modifier.background(MaterialTheme.colorScheme.secondary),
        )
    }
}