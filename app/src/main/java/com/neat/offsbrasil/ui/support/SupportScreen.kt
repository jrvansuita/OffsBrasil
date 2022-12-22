package com.neat.offsbrasil.ui.support

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neat.offsbrasil.R
import com.neat.offsbrasil.theme.AppTheme
import com.neat.offsbrasil.theme.Red


@Preview
@Composable
private fun Preview() {
	AppTheme {
		SupportScreen()
	}
}

@Composable
fun SupportScreen(
) {
	Column(
		modifier = Modifier
			.background(Red)
			.padding(24.dp)
			.fillMaxSize(),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Spacer(modifier = Modifier.weight(.1f))
		Image(
			painter = painterResource(R.drawable.logo), contentDescription = null,
			modifier = Modifier
				.fillMaxWidth()
				.weight(.2f)
		)

		Column(
			modifier = Modifier
				.weight(.6f),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			CreateSupportItemsColumn()
			CreateSupportItemsRow()
		}
	}
}

@Composable
private fun CreateSupportItemsRow() {
	Row(
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.Center,
	) {
		listOf(
			SupportItem.Instagram,
			SupportItem.Facebook,
			SupportItem.Youtube,
			SupportItem.Blog,
		).forEach {
			CreateIcon(it)
		}
	}
}

@Composable
private fun CreateSupportItemsColumn() {
	listOf(
		SupportItem.Phone,
		SupportItem.Whatsapp,
		SupportItem.Email,
		SupportItem.Address
	).forEach {
		CreateTextWithIconLine(it)
	}
}

@Composable
private fun CreateTextWithIconLine(item: SupportItem) {
	val context = LocalContext.current

	item.textRes?.let {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.Center,
			modifier = Modifier
				.clip(MaterialTheme.shapes.medium)
				.clickable { item.onClick(context) }
				.padding(4.dp)
		) {
			Icon(
				painter = painterResource(item.iconRes),
				contentDescription = stringResource(item.textRes),
				tint = Color.White
			)
			Spacer(modifier = Modifier.padding(4.dp))
			Text(text = stringResource(item.textRes), style = MaterialTheme.typography.body2)
		}

		Spacer(modifier = Modifier.padding(4.dp))
	}
}

@Composable
private fun CreateIcon(item: SupportItem) {
	val context = LocalContext.current

	Icon(
		modifier = Modifier
			.size(52.dp)
			.padding(4.dp)
			.clip(MaterialTheme.shapes.medium)
			.clickable { item.onClick(context) },
		painter = painterResource(item.iconRes),
		contentDescription = null,
		tint = Color.White
	)

	val text = remember { mutableStateOf("") }

	TextField(
		value = text.value,
		onValueChange = { text.value = it },
		modifier = Modifier.fillMaxWidth()
	)

	Spacer(modifier = Modifier.padding(8.dp))
}



