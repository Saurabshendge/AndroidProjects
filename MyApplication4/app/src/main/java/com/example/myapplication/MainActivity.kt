package com.example.myapplication

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.SeekBar

class MainActivity : AppCompatActivity() {
    lateinit var runnable: Runnable
    private  var handler=Handler()
private lateinit var mediaPlayer: MediaPlayer
    private lateinit var playButton: ImageButton
    private lateinit var pauseButton: ImageButton
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mediaPlayer=MediaPlayer.create(this,R.raw.music)
        playButton = findViewById(R.id.playButton)
       // pauseButton = findViewById(R.id.pauseButton)
        //----------------------------------
//
//
//
//
//        playButton.setOnClickListener {
//            mediaPlayer.pause()
//            updateButtonVisibility()
//        }
//        pauseButton.setOnClickListener {
//            mediaPlayer.pause()
//            updateButtonVisibility()
//        }
       // updateButtonVisibility()
//mediaPlayer.pause()
           /// updateButtonVisibility()
//
//        --------------------
           val seekbar=SeekBar(this)
        seekbar.progress=0;
        seekbar.max=mediaPlayer.duration
        playButton.setOnClickListener {

            if(!mediaPlayer.isPlaying)
            {
                mediaPlayer.start()
                playButton.setImageResource(R.drawable.baseline_pause_24)
            }else{
                mediaPlayer.pause()
                playButton.setImageResource(R.drawable.baseline_play_arrow_24)
            }
        }
//        seekbar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
//            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, changed: Boolean) {
//                if(changed){
//                    mediaPlayer.seekTo(progress)
//                }
//            }
////
//            override fun onStartTrackingTouch(seekBar: SeekBar?) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onStopTrackingTouch(seekBar: SeekBar?) {
//                TODO("Not yet implemented")
//            }
//        })
//        runnable= Runnable {
//            seekbar.progress=mediaPlayer.currentPosition
//            handler.postDelayed(runnable,1000)
//        }
//        handler.postDelayed(runnable,1000)
//        mediaPlayer.setOnCompletionListener {
//            playButton.setImageResource(R.drawable.baseline_play_arrow_24)
//            seekbar.progress=0
//        }



    }
//    private fun updateButtonVisibility() {
//        if (mediaPlayer.isPlaying) {
//            playButton.visibility = View.GONE
//            pauseButton.visibility = View.VISIBLE
//        } else {
//            playButton.visibility = View.VISIBLE
//            pauseButton.visibility = View.GONE
//        }
//    }
}