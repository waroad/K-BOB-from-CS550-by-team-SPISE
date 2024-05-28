package com.SPISE.k_bob;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;
import java.util.UUID;

public class PersonalSettingsActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "profiles_preferences";
    private static final String ALL_PROFILES_PREFS = "all_profiles";
    private static final String ACTIVE_PROFILE = "active_profile";
    private EditText nameInput;
    private CheckBox checkBeef, checkPork, checkShellfish, checkFish, checkPeanut, checkChicken, checkLamb, checkEgg, checkDairy, checkFlour, checkTreeNuts, checkSoy, checkSesame, checkWheat, checkCorn, checkGluten, checkMustard, checkCelery, checkSulfites, checkLupin;
    private CheckBox checkPreference1, checkPreference2, checkPreference3, checkPreference4, checkPreference5, checkPreference6;
    private String activeProfileId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_settings);
        Intent intent = getIntent();
        Log.d("%%%%%%%%%%","tt: "+intent.getStringExtra("add"));
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
        checkTreeNuts = findViewById(R.id.check_tree_nuts);
        checkSoy = findViewById(R.id.check_soy);
        checkSesame = findViewById(R.id.check_sesame);
        checkWheat = findViewById(R.id.check_wheat);
        checkCorn = findViewById(R.id.check_corn);
        checkGluten = findViewById(R.id.check_gluten);
        checkMustard = findViewById(R.id.check_mustard);
        checkCelery = findViewById(R.id.check_celery);
        checkSulfites = findViewById(R.id.check_sulfites);
        checkLupin = findViewById(R.id.check_lupin);

        checkPreference1 = findViewById(R.id.check_preference_1);
        checkPreference2 = findViewById(R.id.check_preference_2);
        checkPreference3 = findViewById(R.id.check_preference_3);
        checkPreference4 = findViewById(R.id.check_preference_4);
        checkPreference5 = findViewById(R.id.check_preference_5);
        checkPreference6 = findViewById(R.id.check_preference_6);

        // Load the active profile data
        if (Objects.equals(intent.getStringExtra("add"), "False"))
            loadActiveProfileData();

        // Set up listeners for additional preferences
        setupPreferenceListeners();

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
        checkPreference1.setChecked(preferences.getBoolean(activeProfileId + "_is_vegan", false));
        checkPreference2.setChecked(preferences.getBoolean(activeProfileId + "_is_lacto", false));
        checkPreference3.setChecked(preferences.getBoolean(activeProfileId + "_is_ovo", false));
        checkPreference4.setChecked(preferences.getBoolean(activeProfileId + "_is_lacto-ovo", false));
        checkPreference5.setChecked(preferences.getBoolean(activeProfileId + "_is_pollotarian", false));
        checkPreference6.setChecked(preferences.getBoolean(activeProfileId + "_is_pescatarian", false));
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
        checkTreeNuts.setChecked(preferences.getBoolean(activeProfileId + "_avoid_tree_nuts", false));
        checkSoy.setChecked(preferences.getBoolean(activeProfileId + "_avoid_soy", false));
        checkSesame.setChecked(preferences.getBoolean(activeProfileId + "_avoid_sesame", false));
        checkWheat.setChecked(preferences.getBoolean(activeProfileId + "_avoid_wheat", false));
        checkCorn.setChecked(preferences.getBoolean(activeProfileId + "_avoid_corn", false));
        checkGluten.setChecked(preferences.getBoolean(activeProfileId + "_avoid_gluten", false));
        checkMustard.setChecked(preferences.getBoolean(activeProfileId + "_avoid_mustard", false));
        checkCelery.setChecked(preferences.getBoolean(activeProfileId + "_avoid_celery", false));
        checkSulfites.setChecked(preferences.getBoolean(activeProfileId + "_avoid_sulfites", false));
        checkLupin.setChecked(preferences.getBoolean(activeProfileId + "_avoid_lupin", false));
    }

    // Set up listeners for additional preferences
    private void setupPreferenceListeners() {
        CompoundButton.OnCheckedChangeListener preferenceListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    uncheckOtherPreferences(buttonView.getId());
                    applyPreferenceRestrictions(buttonView.getId());
                }
            }
        };

        checkPreference1.setOnCheckedChangeListener(preferenceListener);
        checkPreference2.setOnCheckedChangeListener(preferenceListener);
        checkPreference3.setOnCheckedChangeListener(preferenceListener);
        checkPreference4.setOnCheckedChangeListener(preferenceListener);
        checkPreference5.setOnCheckedChangeListener(preferenceListener);
        checkPreference6.setOnCheckedChangeListener(preferenceListener);
    }

    // Uncheck other preferences when one is checked
    private void uncheckOtherPreferences(int checkedId) {
        if (checkedId != R.id.check_preference_1) checkPreference1.setChecked(false);
        if (checkedId != R.id.check_preference_2) checkPreference2.setChecked(false);
        if (checkedId != R.id.check_preference_3) checkPreference3.setChecked(false);
        if (checkedId != R.id.check_preference_4) checkPreference4.setChecked(false);
        if (checkedId != R.id.check_preference_5) checkPreference5.setChecked(false);
        if (checkedId != R.id.check_preference_6) checkPreference6.setChecked(false);
    }

    // Apply dietary restrictions based on selected preference
    private void applyPreferenceRestrictions(int checkedId) {
        if (checkedId == R.id.check_preference_1) {
            checkBeef.setChecked(true);
            checkPork.setChecked(true);
            checkChicken.setChecked(true);
            checkLamb.setChecked(true);
            checkFish.setChecked(true);
            checkShellfish.setChecked(true);
            checkDairy.setChecked(true);
            checkEgg.setChecked(true);
        }else if (checkedId == R.id.check_preference_2) {
            checkBeef.setChecked(true);
            checkPork.setChecked(true);
            checkChicken.setChecked(true);
            checkLamb.setChecked(true);
            checkFish.setChecked(true);
            checkShellfish.setChecked(true);
            checkDairy.setChecked(false);
            checkEgg.setChecked(true);
        }else if (checkedId == R.id.check_preference_3) {
            checkBeef.setChecked(true);
            checkPork.setChecked(true);
            checkChicken.setChecked(true);
            checkLamb.setChecked(true);
            checkFish.setChecked(true);
            checkShellfish.setChecked(true);
            checkDairy.setChecked(true);
            checkEgg.setChecked(false);
        }else if (checkedId == R.id.check_preference_4) {
            checkBeef.setChecked(true);
            checkPork.setChecked(true);
            checkChicken.setChecked(true);
            checkLamb.setChecked(true);
            checkFish.setChecked(true);
            checkShellfish.setChecked(true);
            checkDairy.setChecked(false);
            checkEgg.setChecked(false);
        }else if (checkedId == R.id.check_preference_5) {
            checkBeef.setChecked(true);
            checkPork.setChecked(true);
            checkChicken.setChecked(false);
            checkLamb.setChecked(true);
            checkFish.setChecked(true);
            checkShellfish.setChecked(true);
            checkDairy.setChecked(false);
            checkEgg.setChecked(false);
        }else if (checkedId == R.id.check_preference_6) {
            checkBeef.setChecked(true);
            checkPork.setChecked(true);
            checkChicken.setChecked(true);
            checkLamb.setChecked(true);
            checkFish.setChecked(false);
            checkShellfish.setChecked(false);
            checkDairy.setChecked(false);
            checkEgg.setChecked(false);
        }
        checkPeanut.setChecked(false);
        checkFlour.setChecked(false);
        checkTreeNuts.setChecked(false);
        checkSoy.setChecked(false);
        checkSesame.setChecked(false);
        checkWheat.setChecked(false);
        checkCorn.setChecked(false);
        checkGluten.setChecked(false);
        checkMustard.setChecked(false);
        checkCelery.setChecked(false);
        checkSulfites.setChecked(false);
        checkLupin.setChecked(false);
    }

    // Save updated preferences for the active profile
    private void savePreferences() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences allProfilesPrefs = getSharedPreferences(ALL_PROFILES_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        SharedPreferences.Editor allProfilesEditor = allProfilesPrefs.edit();

        // Retrieve new profile name input
        String newProfileName = nameInput.getText().toString().trim();
        if (newProfileName.isEmpty()) {
            Toast.makeText(this, "Profile name cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (activeProfileId == null || activeProfileId.isEmpty()) {
            // Create a new profile
            activeProfileId = UUID.randomUUID().toString();
            allProfilesEditor.putString(activeProfileId + "_name", newProfileName);
            prefsEditor.putString(ACTIVE_PROFILE, activeProfileId);
        } else {
            // Update existing profile name
            allProfilesEditor.putString(activeProfileId + "_name", newProfileName);
        }

        // Update or initialize profile preferences

        prefsEditor.putBoolean(activeProfileId + "_is_vegan", checkPreference1.isChecked());
        prefsEditor.putBoolean(activeProfileId + "_is_lacto", checkPreference2.isChecked());
        prefsEditor.putBoolean(activeProfileId + "_is_ovo", checkPreference3.isChecked());
        prefsEditor.putBoolean(activeProfileId + "_is_lacto-ovo", checkPreference4.isChecked());
        prefsEditor.putBoolean(activeProfileId + "_is_pollotarian", checkPreference5.isChecked());
        prefsEditor.putBoolean(activeProfileId + "_is_pescatarian", checkPreference6.isChecked());
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
        prefsEditor.putBoolean(activeProfileId + "_avoid_tree_nuts", checkTreeNuts.isChecked());
        prefsEditor.putBoolean(activeProfileId + "_avoid_soy", checkSoy.isChecked());
        prefsEditor.putBoolean(activeProfileId + "_avoid_sesame", checkSesame.isChecked());
        prefsEditor.putBoolean(activeProfileId + "_avoid_wheat", checkWheat.isChecked());
        prefsEditor.putBoolean(activeProfileId + "_avoid_corn", checkCorn.isChecked());
        prefsEditor.putBoolean(activeProfileId + "_avoid_gluten", checkGluten.isChecked());
        prefsEditor.putBoolean(activeProfileId + "_avoid_mustard", checkMustard.isChecked());
        prefsEditor.putBoolean(activeProfileId + "_avoid_celery", checkCelery.isChecked());
        prefsEditor.putBoolean(activeProfileId + "_avoid_sulfites", checkSulfites.isChecked());
        prefsEditor.putBoolean(activeProfileId + "_avoid_lupin", checkLupin.isChecked());

        // Apply changes
        prefsEditor.apply();
        allProfilesEditor.apply();

        // Show a success message and finish
        Toast.makeText(this, "Profile successfully updated", Toast.LENGTH_SHORT).show();
        finish();
    }
}
