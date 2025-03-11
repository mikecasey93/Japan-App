package com.example.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.game.R

class JapanService: Service() {

    private var soundPlayer: MediaPlayer? = null

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("JapanService", "Service Center")
        soundPlayer = MediaPlayer.create(this, R.raw.gift_confetti).apply {
            isLooping = false
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            "PLAY" -> {
                Log.d("JapanService", "Play action received")
                startForeground()
                soundPlayer?.start()
            }
            "STOP" -> {
                Log.d("JapanService", "Stop Action Received")
                soundPlayer?.pause()
                stopForeground(STOP_FOREGROUND_REMOVE)
                stopSelf()
            }
        }
        return START_STICKY
    }

    private fun startForeground() {
        val channelId = "sound_service"
        val channelName = "Sound Playback"
        val notificationId = 1

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Playing Sound")
            .setContentText("Your sound has played")
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()

        startForeground(notificationId, notification)
    }

    override fun onDestroy() {
        Log.d("JapanService", "Service Destroyed")
        soundPlayer?.release()
        super.onDestroy()
    }
}
