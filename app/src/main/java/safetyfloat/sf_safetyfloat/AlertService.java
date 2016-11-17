package safetyfloat.sf_safetyfloat;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.widget.Toast;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class AlertService extends IntentService {

    public AlertService() {
        super("AlertService");
    }

    public void createNotification(){
        Notification alert;
        NotificationManager nManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);;

        Intent intent = new Intent("Notificação teste");

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 1, intent, 0);

        Notification.Builder builder = new Notification.Builder(getApplicationContext());

        builder.setAutoCancel(false);
        builder.setTicker("Uma enchente pode estar ocorrendo");
        builder.setContentTitle("SafetyFloat Warning");
        builder.setContentText("Uma enchente pode estar ocorrendo");
        builder.setSmallIcon(R.drawable.warning);
        builder.setContentIntent(pendingIntent);
        builder.setOngoing(true);
        builder.build();
        builder.setAutoCancel(true);

        alert = builder.getNotification();
        nManager.notify(11, alert);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int i = 10;
        while(i > 0) {
            Toast toast = Toast.makeText(getApplicationContext(), "serviço inicializado", Toast.LENGTH_SHORT);
            toast.show();
            createNotification();
            i--;
            try {
                Thread.currentThread().sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent,flags,startId);
        return START_REDELIVER_INTENT;
    }
}
