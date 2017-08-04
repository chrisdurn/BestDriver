package chrisdurning.bestdriver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by chrisdurning on 25/06/2017.
 */

public class SmsReceiver extends BroadcastReceiver {

    protected static final String KEY1 = "smsNumbers";
    protected static final String KEY2 = "smsMessages";
    protected static final String KEY3 = "smsTimesReceived";

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onReceive(Context context, Intent intent) {

        if(Utility.getBooleanFromPreferences(context, "safeDrivingPrompt")) {
            // Retrieves a map of extended data from the intent.
            final Bundle bundle = intent.getExtras();

            try {

                if (bundle != null) {

                    final Object[] pdusObj = (Object[]) bundle.get("pdus");
                    String format = bundle.getString("format");
                    for (int i = 0; i < pdusObj.length; i++) {

                        SmsMessage currentMessage = SmsMessage.createFromPdu((byte[] ) pdusObj[i], format);
                        String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                        // number
                        String senderNum = phoneNumber;
                        Utility.addStringToList(context, senderNum, KEY1);
                        // message
                        String message = currentMessage.getDisplayMessageBody();
                        Utility.addStringToList(context, message, KEY2);
                        // time
                        Utility.addStringToList(context, Utility.getCallTime(context), KEY3);

                    }
                }

            } catch (Exception e) {
                Log.e("SmsReceiver", "Exception smsReceiver" +e);

            }
        }
    }
}
