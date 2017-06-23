package chrisdurning.bestdriver;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.MenuItem;
import android.widget.TextView;

import com.skyfishjy.library.RippleBackground;

import mehdi.sakout.fancybuttons.FancyButton;

public class SafeDrivePrompt extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_drive_prompt);


        SharedPreferences settings = getSharedPreferences("prompt", MODE_PRIVATE);
        SharedPreferences.Editor edit = settings.edit();
        edit.putBoolean("prompt", false);
        edit.apply();

        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        final RippleBackground rippleBackground = (RippleBackground)findViewById(R.id.content);

        FancyButton safeDrivingMode = (FancyButton) findViewById(R.id.enable_safe_driving_mode);
        safeDrivingMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rippleBackground.startRippleAnimation();

            }
        });

        FancyButton notNow = (FancyButton) findViewById(R.id.not_now);
        notNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rippleBackground.stopRippleAnimation();
                finish();
            }
        });
    }


    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivityForResult(myIntent, 0);
        return true;

    }
}
