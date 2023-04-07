package com.example.simpleapp.generic.ui

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Subtitle1(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.displayMedium,
        modifier = modifier
    )
}

@Composable
fun Body1StringRes(@StringRes stringResId: Int, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(stringResId),
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier
    )
}

@Composable
fun Body1(text: String, modifier: Modifier = Modifier, textAlign: TextAlign = TextAlign.Center) {
    Text(
        text = text,
        textAlign = textAlign,
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier
    )
}
