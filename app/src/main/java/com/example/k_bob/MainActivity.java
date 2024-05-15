package com.example.k_bob;// MainActivity.java
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {
    private static final int PICK_IMAGE = 100;
    private static final String PREFS_NAME = "profiles_preferences";
    private static final String DARK_MODE_PREF = "dark_mode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        LocaleHelper.loadLocale(this);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isDarkMode = preferences.getBoolean(DARK_MODE_PREF, false);
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        // Initialize the upload button and set up gallery intent
        Button uploadPictureButton = findViewById(R.id.button_upload_picture);
        uploadPictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        // Initialize the settings button for profile management (using fragment navigation)
        ImageButton settingsButton = findViewById(R.id.button_settings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .setCustomAnimations(
                                R.anim.slide_in_right,  // Enter animation
                                R.anim.slide_out_right,  // Exit animation
                                R.anim.slide_in_right,  // Pop enter animation (optional)
                                R.anim.slide_out_right   // Pop exit animation (optional)
                        )
                        .replace(R.id.fragment_container, new SettingsFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        // Initialize the food suggestion list button
        Button foodSuggestionsButton = findViewById(R.id.button_food_suggestions);
        foodSuggestionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to another activity or XML containing the food suggestions
                Intent intent = new Intent(MainActivity.this, FoodSuggestionActivity.class);
                startActivity(intent);
            }
        });
    }

    // Open the gallery to select a photo
    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE);
    }

    // Handle the result of picking an image from the gallery
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            // You can use the imageUri to do further processing
        }
    }
}
