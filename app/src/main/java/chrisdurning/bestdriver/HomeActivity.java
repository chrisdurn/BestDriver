package chrisdurning.bestdriver;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.suke.widget.SwitchButton;

import java.util.Stack;

import mehdi.sakout.fancybuttons.FancyButton;

public class HomeActivity extends Activity {

    private Stack stack = new Stack();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageButton settingsButton = (ImageButton) findViewById(R.id.settings_button);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        FancyButton enableSafeDrivingPrompt = (FancyButton) findViewById(R.id.enable_safe_driving_prompt);
        enableSafeDrivingPrompt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, SafeDrivePrompt.class);
                startActivity(intent);
            }
        });

//        SwitchButton switchButton = (SwitchButton) findViewById(R.id.switch_button);
//
//        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
//                //TODO do your job
//            }
//        });

//        TextView metricHeader = (TextView) findViewById(R.id.metric_header);
//        Typeface t0 = Typeface.createFromAsset(getAssets(), "fonts/HelveticaNeue.ttf");
//        metricHeader.setTypeface(t0);
//
//        TextView avgSpeedTextView = (TextView) findViewById(R.id.user_avg_speed_textview);
//        Typeface t1 = Typeface.createFromAsset(getAssets(), "fonts/HelveticaNeue.ttf");
//        avgSpeedTextView.setTypeface(t1);

        FancyButton clearAvgSpeed = (FancyButton) findViewById(R.id.clear_average_speed_button);
        clearAvgSpeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ActivityRecognitionActivity.class);
                startActivity(intent);
            }
        });

//        TextView distanceTravelledTextView = (TextView) findViewById(R.id.user_distance_travelled_textview);
//        Typeface t2 = Typeface.createFromAsset(getAssets(), "fonts/HelveticaNeue.ttf");
//        distanceTravelledTextView.setTypeface(t2);


        FancyButton clearDistanceTravelled = (FancyButton) findViewById(R.id.clear_distance_travelled);
        clearDistanceTravelled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }



}
