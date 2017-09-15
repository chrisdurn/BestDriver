package chrisdurning.bestdriver;

import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;


public class HomeFragment extends Fragment {
    private HomeFragmentCommunicator mHomeFragmentCommunicator;
    private float averageSpeed;
    private float distance;
    private TextView mAvgSpeedTextView;
    private TextView mUserDistanceTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        mAvgSpeedTextView = (TextView) getActivity().findViewById(R.id.user_avg_speed_textview);
        mUserDistanceTextView = (TextView) getActivity().findViewById(R.id.user_distance_travelled_textview);

        mHomeFragmentCommunicator = (HomeFragmentCommunicator) getActivity();

        ImageButton settingsButton = (ImageButton) getActivity().findViewById(R.id.settings_button);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHomeFragmentCommunicator.replaceWithSettingsFragment();
            }
        });


        FancyButton yourScore = (FancyButton) getActivity().findViewById(R.id.your_score);
        yourScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UserDrivingPoints.class);
                startActivity(intent);
            }
        });



        FancyButton enableSafeDrivingPrompt = (FancyButton) getActivity().findViewById(R.id.enable_safe_driving_prompt);
        enableSafeDrivingPrompt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SafeDrivePrompt.class);
                startActivity(intent);
            }
        });

        FancyButton clearAvgSpeed = (FancyButton) getActivity().findViewById(R.id.clear_average_speed_button);
        clearAvgSpeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.putFloatInPreferences(getActivity(),0.0f, "speed");
                Utility.putIntInPreferences(getActivity(),0, "speedCount");
                averageSpeed = 0.0f;
                setSpeedAndDistance();
            }
        });

        FancyButton clearDistanceTravelled = (FancyButton) getActivity().findViewById(R.id.clear_distance_travelled);
        clearDistanceTravelled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.putFloatInPreferences(getActivity(),0.0f, "distance");
                distance = 0.0f;
                setSpeedAndDistance();

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

        if(Utility.getIntFromPreferences(getActivity(),"speedCount") == 0) {
            averageSpeed = 0.0f;
            distance = 0.0f;
        } else {
            averageSpeed = Utility.getFloatFromPreferences(getActivity(),"speed" ) /
                    Utility.getIntFromPreferences(getActivity(),"speedCount");
            distance = Utility.getFloatFromPreferences(getActivity(),"distance");
        }

        Log.i("Speed: ", "" + Utility.getFloatFromPreferences(getActivity(),"speed" ));
        Log.i("SpeedCount: ", "" + Utility.getIntFromPreferences(getActivity(),"speedCount"));

        Toast.makeText(getActivity(), "Speed: " + Utility.getFloatFromPreferences(getActivity(),"speed" ) + " Count: "
                        + Utility.getIntFromPreferences(getActivity(),"speedCount"),
                        Toast.LENGTH_LONG).show();
        Toast.makeText(getActivity(), "Distance: " + Utility.getFloatFromPreferences(getActivity(),"distance" ),
                Toast.LENGTH_LONG).show();

        setSpeedAndDistance();

    }

    public void setSpeedAndDistance() {
        if(Utility.getBooleanFromPreferences(getActivity().getApplicationContext(),"miles")) {
            mAvgSpeedTextView.setText("Average Speed: " + String.format("%.1f", averageSpeed * 2.23694) + " m/h");
            mUserDistanceTextView.setText("Distance Travelled: " + String.format("%.1f", distance * 0.000621371) + " miles");
        } else {
            mAvgSpeedTextView.setText("Average Speed: " + String.format("%.1f", averageSpeed * 3.6) + " km/h");
            mUserDistanceTextView.setText("Distance Travelled: " + String.format("%.1f", distance * 0.001) + " km");
        }
    }

    interface HomeFragmentCommunicator {
        void replaceWithSettingsFragment();
    }
}

