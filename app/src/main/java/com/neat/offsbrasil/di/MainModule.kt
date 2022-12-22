package com.neat.offsbrasil.di

import com.neat.offsbrasil.BuildConfig
import com.neat.offsbrasil.downloader.DownloaderWrapper
import com.neat.offsbrasil.permission.PermissionWrapper
import com.neat.offsbrasil.ui.main.MainViewModel
import com.neat.offsbrasil.uploader.UploaderWrapper
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
	single { params -> PermissionWrapper(params.get()) }
	single { params -> UploaderWrapper(params.get()) }
	single { DownloaderWrapper(androidContext()) }

	viewModel {
		MainViewModel(BuildConfig.LOGIN_URL)
	}
}