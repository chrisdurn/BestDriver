package chrisdurning.bestdriver;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.MenuItem;
import android.widget.Toast;

import mehdi.sakout.fancybuttons.FancyButton;

public class SafeDrivePrompt extends Activity {
    protected final String KEY = "safeDrivingPrompt";
    private int count = 0;

    private double distance = 0.0;
    private double newLong = 0.0;
    private double newLat = 0.0;
    private double prevLong = 0.0;
    private double prevLat = 0.0;

    private float prefsSpeed = 0.0f;
    private float speed = 0.0f;

    private LocationManager locationManager;
    private LocationListener locationListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_drive_prompt);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        // Define a listener that responds to location updates
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                Toast.makeText(getApplicationContext(), "Speed: " + location.getSpeed() + " Count: " + count,
                        Toast.LENGTH_LONG).show();
                if(Utility.getBooleanFromPreferences(getApplicationContext(), KEY)) {
                    Log.i("*** SafePage Speed: " + location.getSpeed() + " Count: " + count + " ", "onLocationChanged: ");

                    //getting average speed

                    //get from preferences
                    prefsSpeed = Utility.getFloatFromPreferences(getApplicationContext(),"speed");
                    count = Utility.getIntFromPreferences(getApplicationContext(),"speedCount");

                    speed = location.getSpeed();
                    count++;

                    //sum of all speeds
                    speed = speed + prefsSpeed;

                    //adds new speed to prefs
                    Utility.putFloatInPreferences(getApplicationContext(),speed, "speed");
                    Utility.putIntInPreferences( getApplicationContext(),count, "speedCount");

                    //getting distance
                    distance = Utility.getFloatFromPreferences(getApplicationContext(),"distance");

                    newLong = location.getLongitude();
                    newLat = location.getLatitude();

                    if(prevLat != 0.0 && prevLong != 0.0) {
                        distance = distance + getDistanceInKm(prevLat, prevLong, newLat, newLong);
                    }

                    prevLong = newLong;
                    prevLat = newLat;

                    Utility.putFloatInPreferences(getApplicationContext(), (float) distance, "distance");


                }
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
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


        final FancyButton safeDrivingMode = (FancyButton) findViewById(R.id.enable_safe_driving_mode);
        safeDrivingMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.putBooleanInPreferences(getApplicationContext(), true, KEY);
                safeDrivingMode.setBackgroundColor(Color.parseColor("#00E676"));
                Toast.makeText(getApplicationContext(),"Enabled",
                        Toast.LENGTH_SHORT).show();

            }
        });

        FancyButton notNow = (FancyButton) findViewById(R.id.not_now);
        notNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.putBooleanInPreferences(getApplicationContext(), false, KEY);

                locationManager.removeUpdates(locationListener);
                locationManager = null;

                finish();

                Intent intent = new Intent(SafeDrivePrompt.this, SuppressedSummary.class);
                startActivity(intent);
            }
        });
    }

    public double getDistanceInKm(double lat1, double lon1, double lat2, double lon2)
    {
        // Radius of the earth in meters
        final int RADIUS = 6371000;
        // deg2rad below
        double dLat = degreesToRadius(lat2 - lat1);
        double dLon = degreesToRadius(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(degreesToRadius(lat1)) * Math.cos(degreesToRadius(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = RADIUS * c;
        // Distance in km
        return d;
    }

    private double degreesToRadius(double deg)
    {
        return deg * (Math.PI / 180);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Utility.putBooleanInPreferences(getApplicationContext(), false, KEY);
        locationManager.removeUpdates(locationListener);
        locationManager = null;
    }
}
