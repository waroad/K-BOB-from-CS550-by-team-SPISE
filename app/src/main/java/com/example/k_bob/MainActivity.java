package com.example.k_bob;

// MainActivity.java
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI Components
        Button uploadPhotoButton = findViewById(R.id.button_upload_photo);
        ImageButton settingsButton = findViewById(R.id.button_settings);

        // Photo Upload Button OnClick Logic
        uploadPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Placeholder: Start the photo picker/scanner feature
            }
        });

        // Settings Button OnClick Logic
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Placeholder: Navigate to a Settings activity
            }
        });
    }
}
