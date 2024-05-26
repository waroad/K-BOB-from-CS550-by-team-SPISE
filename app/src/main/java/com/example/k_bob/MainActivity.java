package com.example.k_bob;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {
    private static final int PICK_IMAGE = 100;
    private static final int REQUEST_CODE_ADD_PROFILE = 101;
    private static final String PREFS_NAME = "profiles_preferences";
    private static final String DARK_MODE_PREF = "dark_mode";
    private static final String ACTIVE_PROFILE = "active_profile";
    private String currentProfileId;
    private String currentProfileName;

    private void loadProfilePreferences() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        currentProfileId = preferences.getString(ACTIVE_PROFILE, "");
        currentProfileName = preferences.getString(currentProfileId + "_name", "No Profile Selected");
        boolean isDarkMode = preferences.getBoolean(DARK_MODE_PREF, false);
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void setupUi() {
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) getSupportActionBar().hide();
        LocaleHelper.loadLocale(this);
    }

    private void setupListeners() {
        // Set up the click listener for the Upload Picture icon
        findViewById(R.id.uploadPicture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        // Set up the click listener for the Setting icon
        findViewById(R.id.setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ManageProfileActivity.class));
            }
        });

        // Set up the click listener for the Food Suggestion icon
        findViewById(R.id.foodSuggestion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FoodSuggestionActivity.class));
            }
        });

        // Set up the click listener for the Order Translation icon
        findViewById(R.id.orderTranslation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OrderTranslationActivity.class);
                intent.putExtra("activeProfileId", currentProfileId);
                startActivity(intent);
            }
        });
    }

    private void openProfileManagement() {
        Intent intent = new Intent(this, PersonalSettingsActivity.class);
        intent.putExtra("add", "True");
        startActivityForResult(intent, REQUEST_CODE_ADD_PROFILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("%%%%", "wowowowow" + REQUEST_CODE_ADD_PROFILE + "||" + resultCode + "||" + RESULT_OK);
        if (requestCode == REQUEST_CODE_ADD_PROFILE && resultCode == RESULT_OK) {
            // Reload profile preferences if a new profile has been added
            Log.d("%%%%", "wowowowow");
            loadProfilePreferences();
            setupUi();
            setupListeners();
        }
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            // You can use the imageUri to do further processing
            boolean result = true;
            if (result)
                openEdibleActivity();
            else
                openNotEdibleActivity();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadProfilePreferences();
        if (currentProfileId.isEmpty()) {
            openProfileManagement();
        }
        setupUi();
        setupListeners();
    }

    // Open the gallery to select a photo
    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE);
    }

    private void openEdibleActivity() {
        Intent intent = new Intent(this, EdibleActivity.class);
        intent.putExtra("stringA", "can");
        intent.putExtra("stringB", "eat");
        intent.putExtra("stringC", "!");
        startActivity(intent);
    }

    private void openNotEdibleActivity() {
        Intent intent = new Intent(this, EdibleActivity.class);
        intent.putExtra("stringA", "can");
        intent.putExtra("stringB", "not");
        intent.putExtra("stringC", "eat");
        startActivity(intent);
    }
}
