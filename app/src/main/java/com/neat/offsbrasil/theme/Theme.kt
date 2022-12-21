package com.neat.offsbrasil.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val defaultPallet = lightColors(
	primary = PrimaryColor,
	primaryVariant = PrimaryVariantColor,
	secondary = SecondaryColor,
	secondaryVariant = SecondaryVariantColor
)

@Composable
fun AppTheme(
	content: @Composable () -> Unit
) = MaterialTheme(
	colors = defaultPallet,
	typography = Typography,
	shapes = Shapes,
	content = content,
)