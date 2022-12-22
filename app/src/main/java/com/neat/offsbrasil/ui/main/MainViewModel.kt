package com.neat.offsbrasil.ui.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(initUrl: String) : ViewModel() {
	private val _uiState = MutableStateFlow(MainViewState(url = initUrl))
	val uiState: StateFlow<MainViewState> = _uiState.asStateFlow()


	fun onUrlChanged(url: String) {
		_uiState.value = uiState.value.copy(url = url)
	}

}


data class MainViewState(val url: String)