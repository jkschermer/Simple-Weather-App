package com.example.simpleapp.generic.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.example.simpleapp.theme.SimpleAppTheme
import com.example.simpleapp.theme.Spacing.x1
import com.example.simpleapp.theme.Spacing.x2
import com.example.simpleapp.theme.Spacing.x8

@Composable
fun AppToolbar(
    @StringRes textResId: Int,
    @DrawableRes iconResId: Int,
    alignment: Alignment,
    textColor: Color,
    onIconClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = false,
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary)
            .height(x8)
            .fillMaxWidth(),
    ) {
        Image(
            painter = painterResource(iconResId),
            contentDescription = null,
            alignment = alignment,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clickable(onClick = { onIconClick() }, enabled = enabled)
                .padding(start = x1)
        )
        Text(
            text = stringResource(textResId),
            style = MaterialTheme.typography.bodyLarge.merge(TextStyle(fontWeight = FontWeight.Bold)),
            color = textColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = x2)
        )
    }
}

@Composable
fun MainToolbar() {
    AppToolbar(
        textResId = R.string.homescreen_toolbar_title,
        iconResId = R.drawable.globalweather,
        alignment = Alignment.CenterStart,
        textColor = MaterialTheme.colorScheme.background,
        onIconClick = { },
        enabled = false
    )
}

@Composable
fun SecondaryToolbar(
    onClick: () -> Unit
) {
    AppToolbar(
        textResId = R.string.weather_prediction_screen_toolbar,
        iconResId = R.drawable.ic_arrow_back,
        textColor = MaterialTheme.colorScheme.background,
        alignment = Alignment.CenterStart,
        onIconClick = onClick,
        enabled = true,
    )
}

@Preview
@Composable
private fun PreviewToolbar() {
    SimpleAppTheme(darkTheme = true) {
        AppToolbar(
            textResId = R.string.homescreen_toolbar_title,
            iconResId = R.drawable.globalweather,
            alignment = Alignment.CenterStart,
            textColor = MaterialTheme.colorScheme.background,
            onIconClick = {},
        )
    }
}