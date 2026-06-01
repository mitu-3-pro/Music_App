package com.example.api1.player

import android.content.Context
import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer

class AudioPlayerManager(
    private val context: Context
) {

    private val player =
        ExoPlayer.Builder(context).build()

    fun play(localPath: String) {

        val mediaItem =
            MediaItem.fromUri(
                Uri.parse(localPath)
            )

        player.setMediaItem(mediaItem)

        player.prepare()

        player.play()
    }

    fun release() {
        player.release()
    }
}