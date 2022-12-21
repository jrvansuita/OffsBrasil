package com.neat.offsbrasil.bottomnav

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.neat.offsbrasil.R


sealed class BottomNavItems(
	@StringRes val titleRes: Int,
	@DrawableRes val iconRes: Int,
	val route: String
) {
	object Support : BottomNavItems(R.string.support, R.drawable.ic_info, "support")
	object Main : BottomNavItems(R.string.app_name, R.drawable.ic_mic, "main")
	object Share : BottomNavItems(R.string.share, R.drawable.ic_share, "share")
}
