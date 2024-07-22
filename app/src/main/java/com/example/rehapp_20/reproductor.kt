package com.example.rehapp_20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
class reproductor: AppCompatActivity() {

    lateinit var playerView:PlayerView
    lateinit var player:ExoPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reproductor)



        playerView=findViewById(R.id.videoModulo1)
        player=ExoPlayer.Builder(this).build()

        playerView.player=player

        val mediaItem=MediaItem.fromUri("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")

        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }
    override fun onPause() {
        super.onPause()
        player.pause()
    }
    override fun onResume() {
        super.onResume()
        player.play()
    }
    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}