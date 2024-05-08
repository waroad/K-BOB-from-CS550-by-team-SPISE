package com.example.k_bob;// AddProfileActivity.java
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

public class AddProfileActivity extends AppCompatActivity {
    private EditText nameInput;
    private static final String ALL_PROFILES_PREFS = "all_profiles";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_settings);

        // Initialize input field
        nameInput = findViewById(R.id.input_person_name);

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
        SharedPreferences.Editor editor = allProfilesPrefs.edit();
        editor.putString(profileId + "_name", profileName);
        editor.apply();

        // Show a success message and finish
        Toast.makeText(this, "Profile added successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}
