package chrisdurning.bestdriver;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import info.hoang8f.android.segmented.SegmentedGroup;

import static chrisdurning.bestdriver.R.id.miles_button;

public class SettingsActivity extends Activity {
    RadioButton kiloButton;
    RadioButton mileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        kiloButton =(RadioButton)findViewById(R.id.kilos_button);
        mileButton =(RadioButton)findViewById(R.id.miles_button);
        mileButton.setChecked(true);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.segmented2);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                switch(checkedId) {
                    case miles_button:
                            // Pirates are the best
                            mileButton.setChecked(true);
                        break;
                    case R.id.kilos_button:
                            // Ninjas rule
                            kiloButton.setChecked(true);
                        break;
                }
            }
        });


    }
}
