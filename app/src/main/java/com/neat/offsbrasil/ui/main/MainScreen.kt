package com.neat.offsbrasil.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.accompanist.web.AccompanistWebChromeClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import com.neat.offsbrasil.downloader.DownloaderWrapper


@SuppressLint("SetJavaScriptEnabled")
@Composable
fun MainScreen(
	viewState: MainViewState,
	onUrlChanged: (String) -> Unit,
	webChromeClient: AccompanistWebChromeClient,
	downloaderWrapper: DownloaderWrapper
) {

	val webViewState = rememberWebViewState(url = viewState.url)
	val currentUrl = webViewState.content.getCurrentUrl() ?: viewState.url

	if (currentUrl != viewState.url) {
		onUrlChanged(currentUrl)
	}

	WebView(
		state = webViewState,

		onCreated = {
			it.settings.javaScriptEnabled = true
			it.settings.allowContentAccess = true
			it.settings.domStorageEnabled = true
			it.settings.allowFileAccess = true
			it.settings.loadWithOverviewMode = true

		},

		factory = { context ->
			android.webkit.WebView(context).apply {
				setDownloadListener { url, _, _, _, _ ->
					downloaderWrapper.onStarts(url)
				}

			}
		},
		chromeClient = remember { webChromeClient },
		modifier = Modifier.fillMaxSize(),
		captureBackPresses = true,
	)
}


