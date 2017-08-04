package chrisdurning.bestdriver;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ContentResolver;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends Activity implements HomeFragment.HomeFragmentCommunicator{
    private HomeFragment mHomeFragment;
    private SettingsFragment mSettingsFragment;

    private OnBackPressedListener mOnBackPressedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // allows SafeDrivePrompt to load (used in ActivityRecognitionService)
        SharedPreferences settings = getSharedPreferences("prompt", MODE_PRIVATE);
        SharedPreferences.Editor edit = settings.edit();
        edit.putBoolean("prompt", true);
        edit.apply();

        mHomeFragment = new HomeFragment();
        mSettingsFragment = new SettingsFragment();

        replaceFragment(mHomeFragment);

    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

    public void replaceWithHomeFragment() {
        replaceFragment(mHomeFragment);
    }

    @Override
    public void replaceWithSettingsFragment() {
        replaceFragment(mSettingsFragment);
    }

    /**
     * @param onBackPressedListener Gets OnBackPressedListener objects from classes that used back button
     */
    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.mOnBackPressedListener = onBackPressedListener;
    }

    @Override
    protected void onResume() {
        super.onResume();
        // allows SafeDrivePrompt to load (used in ActivityRecognitionService)
        SharedPreferences settings = getSharedPreferences("prompt", MODE_PRIVATE);
        SharedPreferences.Editor edit = settings.edit();
        edit.putBoolean("prompt", true);
        edit.apply();
    }

    @Override
    public void onBackPressed() {
        invalidateOptionsMenu();
        if (mOnBackPressedListener != null) {
            mOnBackPressedListener.onBackPress();
        } else {
            super.onBackPressed();
        }
    }

}
