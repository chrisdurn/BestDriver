package chrisdurning.bestdriver;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.text.LoginFilter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SuppressedSummary extends Activity {
    ArrayList<String> callTimes;
    ArrayList<String> callNumbers;
    ArrayList<String> smsTimes;
    ArrayList<String> smsNumbers;
    ArrayList<String> smsMessage;

    ArrayList<String> numbers;
    ArrayList<String> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suppressed_summary);

        if (Utility.getStringFromPreferences(getApplicationContext(), "numbers") != null) {
            callTimes = Utility.getListOfStrings(getApplicationContext(), "callTimesReceived");
            callNumbers = Utility.getListOfStrings(getApplicationContext(), "numbers");
        }

        if (Utility.getStringFromPreferences(getApplicationContext(), "smsNumbers") != null) {
            smsTimes = Utility.getListOfStrings(getApplicationContext(), "smsTimesReceived");
            smsNumbers = Utility.getListOfStrings(getApplicationContext(), "smsNumbers");
            smsMessage = Utility.getListOfStrings(getApplicationContext(), "smsMessages");
        }

        Log.i("*** ", "sms message: " + smsMessage);
        Log.i("*** ", "call number" + callNumbers);

        ArrayList<String> allTimes = new ArrayList<>();

        // add null checker incase either sms messages or phone calls didn't occur
        if (callTimes != null) {
            allTimes.addAll(callTimes);
        }
        if (smsTimes != null) {
            allTimes.addAll(smsTimes);
        }

        // add this to broadcast receiver class (goes through contacts)
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
        while (phones.moveToNext()) {
            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        }
        phones.close();

        numbers = new ArrayList<>();
        messages = new ArrayList<>();

        if (allTimes != null) {
            Collections.sort(allTimes, Collections.<String>reverseOrder());

            for (int i = 0; i < allTimes.size(); i++) {

                if (callTimes != null) {
                    for (int j = 0; j < callTimes.size(); j++) {
                        if (allTimes.get(i).equals(callTimes.get(j))) {
                            numbers.add(callNumbers.get(j));
                            messages.add("");
                        }
                    }
                }

                if (smsTimes != null) {
                    for (int k = 0; k < smsTimes.size(); k++) {
                        if (allTimes.get(i).equals(smsTimes.get(k))) {
                            numbers.add(smsNumbers.get(k));
                            messages.add(smsMessage.get(k));
                        }
                    }
                }
            }
        }

        Log.i("*** ", "numbers: " + numbers);
        Log.i("*** ", "messages" + messages);
        Log.i("*** ", "allTimes" + allTimes);

        ListView suppressedListView = (ListView) findViewById(R.id.suppressed_list);

        // opens either sms application or call application based on which list item you click on
        suppressedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (messages.get(i).equals("")) {
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                    dialIntent.setData(Uri.parse("tel:" + numbers.get(i)));
                    startActivity(dialIntent);
                } else {
                    Uri smsUri = Uri.parse("smsto:" + numbers.get(i));
                    Intent smsIntent = new Intent(Intent.ACTION_SENDTO, smsUri);
                    smsIntent.putExtra("sms_body", "");
                    startActivity(smsIntent);
                }
           }
       });

        // get data from the table by the ListAdapter
        ListAdapter customAdapter = new ListAdapter(this, R.layout.row_item, numbers, allTimes, messages);

        suppressedListView.setAdapter(customAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Utility.removeStringFromSharedPreferences(getApplicationContext(),"callTimesReceived");
        Utility.removeStringFromSharedPreferences(getApplicationContext(),"numbers");
        Utility.removeStringFromSharedPreferences(getApplicationContext(),"smsTimesReceived");
        Utility.removeStringFromSharedPreferences(getApplicationContext(),"smsNumbers");
        Utility.removeStringFromSharedPreferences(getApplicationContext(),"smsMessages");
    }
}
