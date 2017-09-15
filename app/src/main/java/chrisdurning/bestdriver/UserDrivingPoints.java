package chrisdurning.bestdriver;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.app.Activity;

public class UserDrivingPoints extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_driving_points);

        //NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //mNotificationManager.setNotificationPolicy(1);
    }

}
