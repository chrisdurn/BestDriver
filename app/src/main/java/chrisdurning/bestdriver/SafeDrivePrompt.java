package chrisdurning.bestdriver;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.MenuItem;

import com.skyfishjy.library.RippleBackground;

import mehdi.sakout.fancybuttons.FancyButton;

public class SafeDrivePrompt extends Activity {
    protected final String KEY = "safeDrivingPrompt";
    private int count = 0;
    private Float getSpeed = 0.0f;
    private Float putSpeed = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_drive_prompt);

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                //Log.i("*****", location.getSpeed() + "");
                //Log.i("*****long", location.getLongitude() + "");
                //Log.i("*****latitude", location.getLatitude() + "");

                //Toast.makeText(getApplicationContext(),"Speed: " + location.getSpeed() * 3600/1000 + " km/h",
                        //Toast.LENGTH_SHORT).show();
                putSpeed = location.getSpeed();

                if(count == 0) {
                    //put speed
                    Utility.putFloatInPreferences(getApplicationContext(),location.getSpeed(), "speed");
                } else {
                    //get speed
                    getSpeed = Utility.getFloatFromPreferences(getApplicationContext(),"speed");
                    //put speed
                    Utility.putFloatInPreferences(getApplicationContext(),putSpeed, "speed");
                }

                //put speed
                Utility.putFloatInPreferences(getApplicationContext(),putSpeed, "speed");

                //count
                Utility.putIntInPreferences(getApplicationContext(),count, "count");
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

            // Register the listener with the Location Manager to receive location updates
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

        // stop prompt from popping up while in prompt activity
        Utility.putBooleanInPreferences(getApplicationContext(), false, "prompt");

        final ActionBar actionBar = getActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        final RippleBackground rippleBackground = (RippleBackground)findViewById(R.id.content);

        FancyButton safeDrivingMode = (FancyButton) findViewById(R.id.enable_safe_driving_mode);
        safeDrivingMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rippleBackground.startRippleAnimation();
                Utility.putBooleanInPreferences(getApplicationContext(), true, KEY);
            }
        });

        FancyButton notNow = (FancyButton) findViewById(R.id.not_now);
        notNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rippleBackground.stopRippleAnimation();
                Utility.putBooleanInPreferences(getApplicationContext(), false, KEY);
                //finish();
                Intent intent = new Intent(SafeDrivePrompt.this, SuppressedSummary.class);
                startActivity(intent);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;

    }

}
