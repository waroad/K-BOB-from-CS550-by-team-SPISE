package com.example.k_bob;// AddProfileActivity.java
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

public class AddProfileActivity extends AppCompatActivity {
    private EditText nameInput;
    private static final String ALL_PROFILES_PREFS = "all_profiles";
    private static final String PREFS_NAME = "profiles_preferences";
    private static final String ACTIVE_PROFILE = "active_profile";
    private CheckBox checkBeef, checkPork, checkShellfish, checkFish, checkPeanut, checkChicken, checkLamb, checkEgg, checkDairy, checkFlour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_settings);

        // Initialize input field
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
        // Set up save button
        Button saveButton = findViewById(R.id.button_save_settings);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewProfile();
            }
        });
    }

    // Function to add a new profile
    private void addNewProfile() {
        String profileName = nameInput.getText().toString().trim();
        if (profileName.isEmpty()) {
            Toast.makeText(this, "Profile name cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // Generate a new UUID for this profile
        String profileId = UUID.randomUUID().toString();

        // Save the profile name with its UUID
        SharedPreferences allProfilesPrefs = getSharedPreferences(ALL_PROFILES_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor allProfilesEditor = allProfilesPrefs.edit();
        allProfilesEditor.putString(profileId + "_name", profileName);
        allProfilesEditor.apply();

        // Set the newly created profile as the active profile
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        prefsEditor.putString(ACTIVE_PROFILE, profileId);

        // Initialize default options for the new profile
        prefsEditor.putBoolean(profileId + "_avoid_beef", checkBeef.isChecked());
        prefsEditor.putBoolean(profileId + "_avoid_pork", checkPork.isChecked());
        prefsEditor.putBoolean(profileId + "_avoid_shellfish", checkShellfish.isChecked());
        prefsEditor.putBoolean(profileId + "_avoid_fish", checkFish.isChecked());
        prefsEditor.putBoolean(profileId + "_avoid_peanut", checkPeanut.isChecked());
        prefsEditor.putBoolean(profileId + "_avoid_chicken", checkChicken.isChecked());
        prefsEditor.putBoolean(profileId + "_avoid_lamb", checkLamb.isChecked());
        prefsEditor.putBoolean(profileId + "_avoid_egg", checkEgg.isChecked());
        prefsEditor.putBoolean(profileId + "_avoid_dairy", checkDairy.isChecked());
        prefsEditor.putBoolean(profileId + "_avoid_flour", checkFlour.isChecked());


        // Apply the changes
        prefsEditor.apply();
        // Show a success message and finish
        Toast.makeText(this, "Profile added and switched successfully", Toast.LENGTH_SHORT).show();
        finish(); // Return to the ManageProfileActivity
    }
}