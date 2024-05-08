package com.example.k_bob;// ManageProfileActivity.java
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ManageProfileActivity extends AppCompatActivity {
    private ListView listViewProfiles;
    private List<Profile> profiles;
    private ArrayAdapter<String> adapter;
    private static final String ALL_PROFILES_PREFS = "all_profiles";
    private static final String PREFS_NAME = "profiles_preferences";
    private static final String ACTIVE_PROFILE = "active_profile";
    private Button addProfileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_profile);

        listViewProfiles = findViewById(R.id.list_profiles);
        addProfileButton = findViewById(R.id.button_add_profile);

        // Set up "Add Profile" button listener
        addProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start AddProfileActivity to create a new profile
                Intent intent = new Intent(ManageProfileActivity.this, AddProfileActivity.class);
                startActivity(intent);
            }
        });

        // Retrieve existing profiles
        profiles = getProfilesList();
        updateProfileList();
    }

    // Refresh the profiles list when resuming activity
    @Override
    protected void onResume() {
        super.onResume();
        profiles = getProfilesList();
        updateProfileList();
    }

    // Retrieve all profiles
    private List<Profile> getProfilesList() {
        List<Profile> profiles = new ArrayList<>();
        SharedPreferences allProfilesPrefs = getSharedPreferences(ALL_PROFILES_PREFS, MODE_PRIVATE);

        for (String key : allProfilesPrefs.getAll().keySet()) {
            if (key.endsWith("_name")) {
                String id = key.replace("_name", "");
                String name = allProfilesPrefs.getString(key, "");
                profiles.add(new Profile(id, name));
            }
        }
        return profiles;
    }

    // Update the ListView with the latest profiles
    private void updateProfileList() {
        List<String> profileNames = new ArrayList<>();
        for (Profile profile : profiles) {
            profileNames.add(profile.getName());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, profileNames);
        listViewProfiles.setAdapter(adapter);

        // Handle clicking on an existing profile to switch to it
        listViewProfiles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Profile selectedProfile = profiles.get(position);
                switchToProfile(selectedProfile);
            }
        });
    }

    // Switch to the specified profile by storing the active profile's ID
    private void switchToProfile(Profile profile) {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(ACTIVE_PROFILE, profile.getId());
        editor.apply();

        Toast.makeText(this, "Switched to profile: " + profile.getName(), Toast.LENGTH_SHORT).show();
    }
}
