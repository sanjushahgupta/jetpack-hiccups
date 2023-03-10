package hicCups.p.FirebaseCloudMessaging

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_MUTABLE
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import hicCups.p.MainActivity
import hicCups.p.R
import java.util.*
import kotlin.random.Random.Default.nextInt

class FCMService: FirebaseMessagingService(){
    val CHANNEL_ID = "101"
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        var newtoken = token
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationId  = Random().nextInt()
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        intent.putExtra("name","sam")
        val requestCode = System.currentTimeMillis().toInt()
        var pendingIntent =  PendingIntent.getActivity(this,requestCode,intent ,FLAG_MUTABLE)


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            pendingIntent = PendingIntent.getActivity(this,requestCode,intent ,FLAG_MUTABLE)
            createNotificationChannel(notificationManager)
        }
       /* val pendingIntent = NavDeepLinkBuilder(baseContext)
            .setComponentName(MainActivity::class.java)
            .setGraph(R.navigation.nav_graph)
            .setDestination(R.id.recivedHiccups)
            .createPendingIntent()*/





        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(message.data["title"])
            .setContentText(message.data["message"])
            .setSmallIcon(R.drawable.missing)
           .setContentIntent(pendingIntent)
            .setAutoCancel(true)

            .setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE)
            .build()
        notificationManager.notify(notificationId, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager){
        val channelName = "channelName"

        val channel = NotificationChannel(CHANNEL_ID, channelName, IMPORTANCE_HIGH).apply {
            description = "My channel description"
            enableLights(true)
        }
        notificationManager.createNotificationChannel(channel)
    }
}
