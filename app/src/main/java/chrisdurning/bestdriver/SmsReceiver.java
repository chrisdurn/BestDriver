package chrisdurning.bestdriver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by chrisdurning on 25/06/2017.
 */

public class SmsReceiver extends BroadcastReceiver {

    protected static final String KEY1 = "smsNumbers";
    protected static final String KEY2 = "smsMessages";
    protected static final String KEY3 = "smsTimesReceived";

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onReceive(Context context, Intent intent) {

        SmsMessage [] messages;
        String message = "";
        String phoneNumber = "";

        if(Utility.getBooleanFromPreferences(context, "safeDrivingPrompt")) {
            // Retrieves a map of extended data from the intent.
            final Bundle bundle = intent.getExtras();

            try {

                if (bundle != null) {

                    final Object[] pdusObj = (Object[]) bundle.get("pdus");
                    assert pdusObj != null;
                    messages = new SmsMessage[pdusObj.length];

                    for (int i = 0; i < messages.length; i++) {
                        String format = bundle.getString("format");
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdusObj[i], format);

                        phoneNumber = messages[i].getDisplayOriginatingAddress();
                        message += messages[i].getMessageBody();

                    }
                    Utility.addStringToList(context, phoneNumber, KEY1);
                    Utility.addStringToList(context, message, KEY2);
                    Utility.addStringToList(context, Utility.getCallTime(context), KEY3);
                }

            } catch (RuntimeException e) {
                Log.e("SmsReceiver", e + " ");

            }
        }
    }
}
