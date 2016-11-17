package safetyfloat.sf_safetyfloat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlertBrodcastReceiver extends BroadcastReceiver {
    public AlertBrodcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, AlertService.class));
    }
}
