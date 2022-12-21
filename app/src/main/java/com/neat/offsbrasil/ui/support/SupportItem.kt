package com.neat.offsbrasil.ui.support

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.neat.offsbrasil.R
import com.neat.offsbrasil.extensions.launchBlog
import com.neat.offsbrasil.extensions.launchEmail
import com.neat.offsbrasil.extensions.launchFacebook
import com.neat.offsbrasil.extensions.launchInstagram
import com.neat.offsbrasil.extensions.launchMap
import com.neat.offsbrasil.extensions.launchPhone
import com.neat.offsbrasil.extensions.launchWhatsapp
import com.neat.offsbrasil.extensions.launchYoutube

sealed class SupportItem(
	@StringRes val textRes: Int? = null,
	@DrawableRes val iconRes: Int,
	val onClick: (Context) -> Unit
) {
	object Phone : SupportItem(R.string.phone, R.drawable.ic_phone, {
		it.launchPhone(R.string.phone)
	})

	object Whatsapp : SupportItem(R.string.whatsapp, R.drawable.ic_phone, {
		it.launchWhatsapp(R.string.whatsapp)
	})

	object Email : SupportItem(R.string.email, R.drawable.ic_email, {
		it.launchEmail(R.string.email)
	})

	object Address : SupportItem(R.string.address, R.drawable.ic_location, {
		it.launchMap(R.string.address)
	})

	object Instagram : SupportItem(iconRes = R.drawable.ic_instagram, onClick = {
		it.launchInstagram(R.string.instagram_page_id)
	})

	object Facebook : SupportItem(iconRes = R.drawable.ic_facebook, onClick = {
		it.launchFacebook(R.string.facebook_page_id)
	})

	object Youtube : SupportItem(iconRes = R.drawable.ic_youtube, onClick = {
		it.launchYoutube(R.string.youtube)
	})

	object Blog : SupportItem(iconRes = R.drawable.ic_blog, onClick = {
		it.launchBlog(R.string.blog)
	})
}