package chrisdurning.bestdriver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.DetectedActivity;

import java.util.ArrayList;

/**
 * Created by chrisdurning on 17/06/2017.
 */

public class NumberReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            Log.i(number, " *****************");
        }

    }


}
