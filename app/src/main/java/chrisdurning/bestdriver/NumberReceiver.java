package chrisdurning.bestdriver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

/**
 * Created by chrisdurning on 17/06/2017.
 */

public class NumberReceiver extends BroadcastReceiver {

    protected static final String KEY1 = "numbers";
    protected static final String KEY2 = "callTimesReceived";

    @Override
    public void onReceive(final Context context, Intent intent) {

        if(Utility.getBooleanFromPreferences(context, "safeDrivingPrompt")) {
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {

                String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);

                Utility.addStringToList(context, number, KEY1);
                Utility.addStringToList(context, Utility.getCallTime(context), KEY2);

            }
        }
    }
}
