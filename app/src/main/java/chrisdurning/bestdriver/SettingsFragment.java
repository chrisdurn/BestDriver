package chrisdurning.bestdriver;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.suke.widget.SwitchButton;

import static chrisdurning.bestdriver.R.id.miles_button;

public class SettingsFragment extends Fragment implements OnBackPressedListener{
    RadioButton kiloButton;
    RadioButton mileButton;
    SwitchButton switchButton;
    Boolean check = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setOnBackPressedListener(this);
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        kiloButton =(RadioButton) getActivity().findViewById(R.id.kilos_button);
        mileButton =(RadioButton)getActivity().findViewById(R.id.miles_button);
        mileButton.setChecked(true);

        RadioGroup radioGroup = (RadioGroup) getActivity().findViewById(R.id.segmented2);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                switch(checkedId) {
                    case miles_button:
                        // Pirates are the best
                        mileButton.setChecked(true);
                        Utility.putBooleanInPreferences(getActivity().getApplicationContext(), true, "miles");
                        break;
                    case R.id.kilos_button:
                        // Ninjas rule
                        kiloButton.setChecked(true);
                        Utility.putBooleanInPreferences(getActivity().getApplicationContext(), false, "miles");
                        break;
                }
            }
        });


        switchButton = (SwitchButton) getActivity().findViewById(R.id.switch_button);
        switchButton.setChecked(check);

        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                check = isChecked;
                if(check) {
                    Utility.putBooleanInPreferences(getActivity().getApplicationContext(), false, "passenger");
                } else {
                    Utility.putBooleanInPreferences(getActivity().getApplicationContext(), true, "passenger");
                }

            }
        });
    }


    @Override
    public void onBackPress() {
        ((MainActivity) getActivity()).replaceWithHomeFragment();
    }
}
