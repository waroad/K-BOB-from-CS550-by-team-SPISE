package com.example.k_bob;// PersonalSettingsActivity.java
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PersonalSettingsActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "profiles_preferences";
    private static final String ALL_PROFILES_PREFS = "all_profiles";
    private EditText nameInput;
    private CheckBox checkBeef, checkPork, checkShellfish, checkFish, checkPeanut, checkChicken, checkLamb, checkEgg, checkDairy, checkFlour;
    private String activeProfileId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_settings);

        // Initialize UI components
        nameInput = findViewById(R.id.input_person_name);
        checkBeef = findViewById(R.id.check_beef);
        checkPork = findViewById(R.id.check_pork);
        checkShellfish = findViewById(R.id.check_shellfish);
        checkFish = findViewById(R.id.check_fish);
        checkPeanut = findViewById(R.id.check_peanut);
        checkChicken = findViewById(R.id.check_chicken);
        checkLamb = findViewById(R.id.check_lamb);
        checkEgg = findViewById(R.id.check_egg);
        checkDairy = findViewById(R.id.check_dairy);
        checkFlour = findViewById(R.id.check_flour);

        // Load the active profile data
        loadActiveProfileData();

        // Set up save button
        Button saveButton = findViewById(R.id.button_save_settings);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePreferences();
            }
        });
    }

    // Load data for the active profile
    private void loadActiveProfileData() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences allProfilesPrefs = getSharedPreferences(ALL_PROFILES_PREFS, MODE_PRIVATE);
        activeProfileId = preferences.getString("active_profile", "");

        if (activeProfileId.isEmpty()) {
            return; // No active profile selected
        }

        // Get the name associated with the active profile ID
        String activeProfileName = allProfilesPrefs.getString(activeProfileId + "_name", "");

        // Set the name field and checkboxes according to the active profile
        nameInput.setText(activeProfileName);
        checkBeef.setChecked(preferences.getBoolean(activeProfileId + "_avoid_beef", false));
        checkPork.setChecked(preferences.getBoolean(activeProfileId + "_avoid_pork", false));
        checkShellfish.setChecked(preferences.getBoolean(activeProfileId + "_avoid_shellfish", false));
        checkFish.setChecked(preferences.getBoolean(activeProfileId + "_avoid_fish", false));
        checkPeanut.setChecked(preferences.getBoolean(activeProfileId + "_avoid_peanut", false));
        checkChicken.setChecked(preferences.getBoolean(activeProfileId + "_avoid_chicken", false));
        checkLamb.setChecked(preferences.getBoolean(activeProfileId + "_avoid_lamb", false));
        checkEgg.setChecked(preferences.getBoolean(activeProfileId + "_avoid_egg", false));
        checkDairy.setChecked(preferences.getBoolean(activeProfileId + "_avoid_dairy", false));
        checkFlour.setChecked(preferences.getBoolean(activeProfileId + "_avoid_flour", false));
    }

    // Save updated preferences for the active profile
    private void savePreferences() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences allProfilesPrefs = getSharedPreferences(ALL_PROFILES_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        SharedPreferences.Editor allProfilesEditor = allProfilesPrefs.edit();

        // Update name and other preferences for the active profile
        String newProfileName = nameInput.getText().toString().trim();
        allProfilesEditor.putString(activeProfileId + "_name", newProfileName);

        prefsEditor.putBoolean(activeProfileId + "_avoid_beef", checkBeef.isChecked());
        prefsEditor.putBoolean(activeProfileId + "_avoid_pork", checkPork.isChecked());
        prefsEditor.putBoolean(activeProfileId + "_avoid_shellfish", checkShellfish.isChecked());
        prefsEditor.putBoolean(activeProfileId + "_avoid_fish", checkFish.isChecked());
        prefsEditor.putBoolean(activeProfileId + "_avoid_peanut", checkPeanut.isChecked());
        prefsEditor.putBoolean(activeProfileId + "_avoid_chicken", checkChicken.isChecked());
        prefsEditor.putBoolean(activeProfileId + "_avoid_lamb", checkLamb.isChecked());
        prefsEditor.putBoolean(activeProfileId + "_avoid_egg", checkEgg.isChecked());
        prefsEditor.putBoolean(activeProfileId + "_avoid_dairy", checkDairy.isChecked());
        prefsEditor.putBoolean(activeProfileId + "_avoid_flour", checkFlour.isChecked());

        // Apply changes
        prefsEditor.apply();
        allProfilesEditor.apply();

        // Show a success message and return to the previous screen
        Toast.makeText(this, "Profile successfully updated", Toast.LENGTH_SHORT).show();
        finish();
    }
}
