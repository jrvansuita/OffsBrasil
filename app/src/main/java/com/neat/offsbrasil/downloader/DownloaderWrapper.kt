package com.neat.offsbrasil.downloader

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.neat.offsbrasil.R


class DownloaderWrapper(private val context: Context) {

	fun onStarts(url: String?) {
		Toast.makeText(
			context,
			context.getString(R.string.download_success),
			Toast.LENGTH_LONG
		).show()

		ContextCompat.startActivity(context, Intent(Intent.ACTION_VIEW).apply {
			flags = Intent.FLAG_ACTIVITY_NEW_TASK
			data = Uri.parse(url)
		}, null)
	}
}