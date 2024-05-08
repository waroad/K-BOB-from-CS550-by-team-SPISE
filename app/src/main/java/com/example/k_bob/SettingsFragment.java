package com.example.k_bob;

// SettingsFragment.java
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

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
}
