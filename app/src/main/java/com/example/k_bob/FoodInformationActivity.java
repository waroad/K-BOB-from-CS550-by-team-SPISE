package com.example.k_bob;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FoodInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_information);

        String foodName = getIntent().getStringExtra("foodName");

        ImageView foodImageView = findViewById(R.id.food_image);
        TextView foodNameTextView = findViewById(R.id.food_name);
        TextView descriptionTextView = findViewById(R.id.description);
        TextView keyComponentsTextView = findViewById(R.id.key_components);

        int foodImageResId = getFoodImageResourceId(foodName);

        foodImageView.setImageResource(foodImageResId);
        foodNameTextView.setText(foodName);
        descriptionTextView.setText("Description of " + foodName);
        keyComponentsTextView.setText("Key components of " + foodName);

        findViewById(R.id.back_icon).setOnClickListener(v -> finish());

        findViewById(R.id.map_icon).setOnClickListener(v -> {
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(foodName + " restaurant"));
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        });
    }

    private int getFoodImageResourceId(String foodName) {
        switch (foodName) {
            case "Pizza":
                return R.drawable.pizza;
            default:
                return R.drawable.default_food;
        }
    }
}
