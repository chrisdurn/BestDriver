package chrisdurning.bestdriver;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import mehdi.sakout.fancybuttons.FancyButton;


public class HomeFragment extends Fragment {
    private HomeFragmentCommunicator mHomeFragmentCommunicator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Float averageSpeed = Utility.getFloatFromPreferences(getActivity().getApplicationContext(),
                "speed");
        int count = Utility.getIntFromPreferences(getActivity().getApplicationContext(),"count");
        mHomeFragmentCommunicator = (HomeFragmentCommunicator) getActivity();

        ImageButton settingsButton = (ImageButton) getActivity().findViewById(R.id.settings_button);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHomeFragmentCommunicator.replaceWithSettingsFragment();
            }
        });

        TextView avgSpeedTextView = (TextView) getActivity().findViewById(R.id.user_avg_speed_textview);
        TextView userDistanceTextView = (TextView) getActivity().findViewById(R.id.user_distance_travelled_textview);

        if(Utility.getBooleanFromPreferences(getActivity().getApplicationContext(),"miles")) {
            avgSpeedTextView.setText("Average Speed: 0 m/h");
            userDistanceTextView.setText("Distance Travelled: 0 miles");
        } else {
            avgSpeedTextView.setText("Average Speed: 0 km/h");
            userDistanceTextView.setText("Distance Travelled: 0 kilometers");
        }



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

            }
        });

        FancyButton clearDistanceTravelled = (FancyButton) getActivity().findViewById(R.id.clear_distance_travelled);
        clearDistanceTravelled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    interface HomeFragmentCommunicator {
        void replaceWithSettingsFragment();
    }
}

