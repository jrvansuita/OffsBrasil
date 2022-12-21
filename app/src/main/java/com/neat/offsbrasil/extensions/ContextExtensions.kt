package com.neat.offsbrasil.extensions

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.StringRes
import com.neat.offsbrasil.R


fun Context.launchPhone(@StringRes phoneRes: Int) {
	val phone = getString(phoneRes).filter { it.isDigit() }

	startActivity(
		Intent(
			Intent.ACTION_DIAL,
			Uri.fromParts("tel", phone, null)
		)
	)
}

fun Context.launchWhatsapp(@StringRes phoneRes: Int) {
	val phone = getString(phoneRes).filter { it.isDigit() }
	startActivity(
		Intent(
			Intent.ACTION_VIEW,
			Uri.parse(
				"https://wa.me/+55$phone/?text=${getString(R.string.whatsapp_message)}"
			)
		)
	)
}

fun Context.launchEmail(@StringRes emailRes: Int) {
	startActivity(Intent(Intent.ACTION_SEND).apply {
		type = "*/*"
		putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(emailRes)))
		putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name))
	})
}

fun Context.launchMap(@StringRes addressRes: Int) {
	startActivity(Intent(Intent.ACTION_VIEW).apply {
		data = Uri.parse("geo:0,0?q=${getString(addressRes)}")
	})
}

fun Context.launchInstagram(@StringRes pageIdRes: Int) {
	val uri = Uri.parse("http://instagram.com/_u/${getString(pageIdRes)}")
	val likeIng = Intent(Intent.ACTION_VIEW, uri)

	likeIng.setPackage("com.instagram.android")

	try {
		startActivity(likeIng)
	} catch (e: ActivityNotFoundException) {
		startActivity(
			Intent(
				Intent.ACTION_VIEW,
				Uri.parse("http://instagram.com/${getString(pageIdRes)}")
			)
		)
	}
}

fun Context.launchFacebook(@StringRes pageIdRes: Int) {
	try {
		startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/${getString(pageIdRes)}")))
	} catch (e: Exception) {
		startActivity(
			Intent(
				Intent.ACTION_VIEW,
				Uri.parse("https://www.facebook.com/${getString(pageIdRes)}")
			)
		)
	}
}


fun Context.launchYoutube(@StringRes channelUrlRes: Int) {
	startActivity(
		Intent(
			Intent.ACTION_VIEW,
			Uri.parse(getString(channelUrlRes))
		)
	)
}


fun Context.launchBlog(@StringRes urlRes: Int) {
	startActivity(
		Intent(
			Intent.ACTION_VIEW,
			Uri.parse(getString(urlRes))
		)
	)
}

fun Context.launchShareApp(@StringRes message: Int) {
	val intent = Intent(Intent.ACTION_SEND)
	intent.type = "text/plain"
	intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name))
	intent.putExtra(Intent.EXTRA_TEXT, getString(message))
	startActivity(Intent.createChooser(intent, getString(R.string.share)))
}