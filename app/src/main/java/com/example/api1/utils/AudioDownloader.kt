package com.example.api1.utils

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment

object AudioDownloader {

    fun downloadAudio(
        context: Context,
        audioUrl: String,
        fileName: String
    ): Long {

        val request = DownloadManager.Request(
            Uri.parse(audioUrl)
        )

        request.setTitle(fileName)

        request.setNotificationVisibility(
            DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED
        )

        request.setDestinationInExternalFilesDir(
            context,
            Environment.DIRECTORY_MUSIC,
            "$fileName.mp3"
        )

        val manager =
            context.getSystemService(
                Context.DOWNLOAD_SERVICE
            ) as DownloadManager

        return manager.enqueue(request)
    }
}