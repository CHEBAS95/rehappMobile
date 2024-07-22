package com.example.rehapp_20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.VideoView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

class modulo14 : AppCompatActivity() {

    lateinit var playerView: PlayerView
    lateinit var player:ExoPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modulo14)

        val txt: ImageView  = findViewById(R.id.imagemodulo_play)
        txt.setOnClickListener {

            val intent: Intent = Intent(this, reproductor:: class.java)
            startActivity(intent)

        }

        val txt1: ImageView  = findViewById(R.id.nav_back)
        txt1.setOnClickListener {

            val intent: Intent = Intent(this, final_Modulo1:: class.java)
            startActivity(intent)

        }



        playerView=findViewById(R.id.videoBienvenida)
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
