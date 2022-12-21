package com.neat.offsbrasil.permission

import android.Manifest
import android.app.Activity
import com.neat.offsbrasil.R
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.annotations.AfterPermissionGranted


private const val REQUEST_CODE_PERMISSION = 999

class PermissionWrapper(private val activity: Activity) {
	
	@AfterPermissionGranted(REQUEST_CODE_PERMISSION)
	fun methodRequiresPermissions() {
		val permissions = listOf(
			Manifest.permission.RECORD_AUDIO,
			Manifest.permission.WRITE_EXTERNAL_STORAGE,
			Manifest.permission.MODIFY_AUDIO_SETTINGS
		)

		if (EasyPermissions.hasPermissions(
				activity,
				*permissions.toTypedArray()
			)
		) {
		} else {
			// Do not have permissions, request them now
			EasyPermissions.requestPermissions(
				host = activity,
				rationale = activity.getString(R.string.give_permissions),
				requestCode = REQUEST_CODE_PERMISSION,
				perms = permissions.toTypedArray()
			)
		}
	}
}