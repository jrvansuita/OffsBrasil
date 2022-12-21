package com.neat.offsbrasil

import android.app.Application
import com.neat.offsbrasil.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class OffsBrasilApplication : Application() {
	override fun onCreate() {
		super.onCreate()
		startKoin {
			androidContext(this@OffsBrasilApplication)

			modules(
				mainModule
			)

		}
	}
}
