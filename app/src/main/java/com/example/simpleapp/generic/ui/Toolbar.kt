package com.example.simpleapp.generic.ui

import androidx.annotation.DrawableRes
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.simpleapp.R
import com.example.simpleapp.theme.SimpleAppTheme
import com.example.simpleapp.theme.Spacing.x1
import com.example.simpleapp.theme.Spacing.x2
import com.example.simpleapp.theme.Spacing.x8

@Composable
fun AppToolbar(
    @DrawableRes iconResId: Int,
    text: String,
    alignment: Alignment,
    textColor: Color,
    onIconClick: () -> Unit,
    modifier: Modifier = Modifier,
    paddingValue: Dp = 0.dp,
    textAlign: TextAlign = TextAlign.Center,
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary)
            .height(x8)
            .fillMaxWidth()
            .padding(horizontal = x1),
    ) {
        Image(
            painter = painterResource(iconResId),
            contentDescription = null,
            alignment = alignment,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clickable { onIconClick() }
                .padding(end = paddingValue)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge.merge(TextStyle(fontWeight = FontWeight.Bold)),
            color = textColor,
            textAlign = textAlign,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun MainToolbar(modifier: Modifier = Modifier) {
    AppToolbar(
        text = stringResource(R.string.homescreen_toolbar_title),
        iconResId = R.drawable.ic_logo_round,
        textAlign = TextAlign.Start,
        alignment = Alignment.CenterStart,
        paddingValue = x2,
        textColor = MaterialTheme.colorScheme.background,
        modifier = modifier,
        onIconClick = { }
    )
}

@Composable
fun SecondaryToolbar(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AppToolbar(
        text = stringResource(R.string.weather_prediction_screen_toolbar),
        iconResId = R.drawable.ic_arrow_back,
        textColor = MaterialTheme.colorScheme.background,
        textAlign = TextAlign.Center,
        alignment = Alignment.CenterStart,
        onIconClick = onClick,
        modifier = modifier
    )
}

@Preview
@Composable
private fun PreviewToolbar() {
    SimpleAppTheme(darkTheme = true) {
        AppToolbar(
            text = stringResource(R.string.homescreen_toolbar_title),
            iconResId = R.drawable.globalweather,
            alignment = Alignment.CenterStart,
            textColor = MaterialTheme.colorScheme.background,
            onIconClick = {},
        )
    }
}

@Preview
@Composable
private fun PreviewSecondToolbar() {
    SimpleAppTheme(darkTheme = true) {
        SecondaryToolbar(onClick = {})
    }
}