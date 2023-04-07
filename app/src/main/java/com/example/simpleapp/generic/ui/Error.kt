package com.example.simpleapp.generic.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.simpleapp.R

@Composable
fun ErrorContent() {
    Text(stringResource(R.string.main_error_message))
}
