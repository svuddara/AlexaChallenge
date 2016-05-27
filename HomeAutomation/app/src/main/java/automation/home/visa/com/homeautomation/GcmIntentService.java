package automation.home.visa.com.homeautomation;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import automation.home.visa.com.homeautomation.activity.MainActivity;

/**
 * Created by Sowmya on 5/9/16.
 */
public class GcmIntentService extends IntentService {

    public static String fragmenttoLoad = "checkout";

    public GcmIntentService() {
        super("GcmIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        String messageType = gcm.getMessageType(intent);

        String s = extras.getString("message");
        if (!extras.isEmpty() && GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {

            if(s.contains("flowers")) {
                fragmenttoLoad = "flowers";
            }else{
                fragmenttoLoad= "checkout";
            }

            sendNotification("Alexa Notification",s);

            Log.i("push", extras.toString());
        }
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }

    private void sendNotification(String title,String message) {
        NotificationManager mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        Notification n  = new Notification.Builder(this)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.noticon)
                .setContentText(message)
                .setContentIntent(contentIntent)
                .setAutoCancel(true).build();

        mNotificationManager.notify(123, n);
    }


}