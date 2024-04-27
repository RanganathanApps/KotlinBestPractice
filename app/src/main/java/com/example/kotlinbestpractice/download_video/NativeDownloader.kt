package com.example.kotlinbestpractice.download_video

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri

/** Created By Ranga
on 26-04-2024
 **/
class NativeDownloader(
    private val context: Context
) : Downloader {

    val downloadManager = context.getSystemService(DownloadManager::class.java)
    override fun downloadFile(url: String): Long {
        val request = DownloadManager.Request(url.toUri())
            .setMimeType("video/mp4")
            .setTitle("video download")
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"videos.flv")
        return downloadManager.enqueue(request)
    }
}