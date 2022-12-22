package com.neat.offsbrasil

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.fragment.app.FragmentActivity
import com.neat.offsbrasil.bottomnav.MainNavigationScreen
import com.neat.offsbrasil.downloader.DownloaderWrapper
import com.neat.offsbrasil.permission.PermissionWrapper
import com.neat.offsbrasil.ui.main.MainViewModel
import com.neat.offsbrasil.uploader.UploaderWrapper
import com.vmadalin.easypermissions.EasyPermissions
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class MainActivity : FragmentActivity() {

	private val permissionWrapper by inject<PermissionWrapper> { parametersOf(this) }
	private val uploaderWrapper by inject<UploaderWrapper> { parametersOf(this) }
	private val downloaderWrapper by inject<DownloaderWrapper>()
	private val viewModel by viewModel<MainViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		permissionWrapper.methodRequiresPermissions()

		setContent {
			val state by viewModel.uiState.collectAsState()

			MainNavigationScreen(
				state,
				viewModel::onUrlChanged,
				uploaderWrapper.webChromeClient,
				downloaderWrapper
			)
		}
	}

	override fun onRequestPermissionsResult(
		requestCode: Int,
		permissions: Array<out String>,
		grantResults: IntArray
	) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults)
		EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
		super.onActivityResult(requestCode, resultCode, intent)
		uploaderWrapper.onActivityResult(requestCode, resultCode, intent)
	}
}