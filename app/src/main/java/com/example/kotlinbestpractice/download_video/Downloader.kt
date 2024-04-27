package com.example.kotlinbestpractice.download_video

interface Downloader {
    fun downloadFile(url: String) : Long
}