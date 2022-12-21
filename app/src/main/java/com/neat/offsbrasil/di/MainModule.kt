package com.neat.offsbrasil.di

import com.neat.offsbrasil.downloader.DownloaderWrapper
import com.neat.offsbrasil.permission.PermissionWrapper
import com.neat.offsbrasil.uploader.UploaderWrapper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val mainModule = module {
	single { params -> PermissionWrapper(params.get()) }
	single { params -> UploaderWrapper(params.get()) }
	single { DownloaderWrapper(androidContext()) }
}