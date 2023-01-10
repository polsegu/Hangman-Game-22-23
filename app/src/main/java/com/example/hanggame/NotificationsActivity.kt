package com.example.hanggame

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import androidx.core.app.NotificationCompat

class NotificationsActivity : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Aquí es donde debes escribir el código para realizar la tarea
        // del servicio, como descargar un archivo o reproducir música

        createNotificationLogin(this)
        createNotificationRegister(this)
        createNotificationWin(this)
        createNotificationLose(this)

        return Service.START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun createNotificationLogin(context: Context) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notificationId = 1
        val channelId = "hangman_channel_01"

        // Create the notification channel
        val channel = NotificationChannel(channelId, "Hangman channel", NotificationManager.IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(channel)

        // Create the notification
        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Hangman Game")
            .setContentText("You are playing Hangman!")

        // Create the intent that will be launched when the user taps the notification
        val intent = Intent(context, NotificationsActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        builder.setContentIntent(pendingIntent)

        // Show the notification
        notificationManager.notify(notificationId, builder.build())
    }

    private fun createNotificationRegister(context: Context) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notificationId = 1
        val channelId = "hangman_channel_01"

        // Create the notification channel
        val channel = NotificationChannel(channelId, "Hangman channel", NotificationManager.IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(channel)

        // Create the notification
        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Hangman Game")
            .setContentText("Account created!")

        // Create the intent that will be launched when the user taps the notification
        val intent = Intent(context, NotificationsActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        builder.setContentIntent(pendingIntent)

        // Show the notification
        notificationManager.notify(notificationId, builder.build())
    }

    private fun createNotificationWin(context: Context) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notificationId = 1
        val channelId = "hangman_channel_01"

        // Create the notification channel
        val channel = NotificationChannel(channelId, "Hangman channel", NotificationManager.IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(channel)

        // Create the notification
        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Hangman Game")
            .setContentText("Wow you are a beast!")

        // Create the intent that will be launched when the user taps the notification
        val intent = Intent(context, NotificationsActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        builder.setContentIntent(pendingIntent)

        // Show the notification
        notificationManager.notify(notificationId, builder.build())
    }

    private fun createNotificationLose(context: Context) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notificationId = 1
        val channelId = "hangman_channel_01"

        // Create the notification channel
        val channel = NotificationChannel(channelId, "Hangman channel", NotificationManager.IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(channel)

        // Create the notification
        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Hangman Game")
            .setContentText("Oh! You lose!")

        // Create the intent that will be launched when the user taps the notification
        val intent = Intent(context, NotificationsActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        builder.setContentIntent(pendingIntent)

        // Show the notification
        notificationManager.notify(notificationId, builder.build())
    }

    // Para utilizarlo
    // val intent = Intent(this, NotificationsActivity::class.java)
    // startService(intent)
}