package com.example.k_bob;

// SettingsFragment.java
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {
    private static final String PREFS_NAME = "profiles_preferences";
    private static final String ALL_PROFILES_PREFS = "all_profiles";
    private static final String ACTIVE_PROFILE = "active_profile";
    private TextView profileNameText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        view.setClickable(true); // Ensure the fragment intercepts touch events

        // Initialize profile name TextView
        profileNameText = view.findViewById(R.id.text_current_profile_name);

        // Retrieve current profile name and display it
        SharedPreferences preferences = requireActivity().getSharedPreferences(PREFS_NAME, getContext().MODE_PRIVATE);
        SharedPreferences allProfilesPrefs = requireActivity().getSharedPreferences(ALL_PROFILES_PREFS, getContext().MODE_PRIVATE);
        String activeProfileId = preferences.getString(ACTIVE_PROFILE, "");
        String currentProfileName = allProfilesPrefs.getString(activeProfileId + "_name", "No Profile Selected");

        profileNameText.setText("Current Profile: " + currentProfileName);

        // Initialize buttons
        Button manageProfileButton = view.findViewById(R.id.button_manage_profile);
        Button personalSettingsButton = view.findViewById(R.id.button_personal_settings);
        Button darkModeButton = view.findViewById(R.id.button_dark_mode);
        Button languageButton = view.findViewById(R.id.button_language);

        // Manage Profile
        manageProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ManageProfileActivity.class);
                startActivity(intent);
            }
        });

        // Personal Settings
        personalSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the PersonalSettingsActivity
                Intent intent = new Intent(getActivity(), PersonalSettingsActivity.class);
                startActivity(intent);
            }
        });

        // Enable Dark Mode
        darkModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle dark mode functionality (simple placeholder)
            }
        });

        // Change Language
        languageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle language switching logic
            }
        });

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        // Retrieve current profile name from shared preferences
        SharedPreferences preferences = requireActivity().getSharedPreferences(PREFS_NAME, getContext().MODE_PRIVATE);
        SharedPreferences allProfilesPrefs = requireActivity().getSharedPreferences(ALL_PROFILES_PREFS, getContext().MODE_PRIVATE);
        String activeProfileId = preferences.getString(ACTIVE_PROFILE, "");
        String currentProfileName = allProfilesPrefs.getString(activeProfileId + "_name", "No Profile Selected");

        // Update profile name text view
        profileNameText.setText("Current Profile: " + currentProfileName);
    }
}
