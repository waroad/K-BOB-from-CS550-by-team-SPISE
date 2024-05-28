package com.example.k_bob;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FoodInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_information);

        String foodName = getIntent().getStringExtra("foodName");
        int foodImageResId = getIntent().getIntExtra("foodImageResId", -1);
        String detailedDescription = getIntent().getStringExtra("detailedDescription");
        String majorIngredients = getIntent().getStringExtra("majorIngredients");
        String veganType = getIntent().getStringExtra("veganType");
        String dietaryRestrictions = getIntent().getStringExtra("dietaryRestrictions");

        ImageView foodImageView = findViewById(R.id.food_image);
        TextView foodNameTextView = findViewById(R.id.food_name);
        TextView descriptionTextView = findViewById(R.id.description);
        TextView keyComponentsTextView = findViewById(R.id.key_components);
        TextView veganTypeTextView = findViewById(R.id.vegan_type);
        TextView dietaryRestrictionsTextView = findViewById(R.id.dietary_restrictions);

        foodImageView.setImageResource(foodImageResId);
        foodNameTextView.setText(foodName);
        descriptionTextView.setText(detailedDescription);
        keyComponentsTextView.setText(majorIngredients);
        veganTypeTextView.setText(veganType);
        dietaryRestrictionsTextView.setText(dietaryRestrictions);

        findViewById(R.id.back_icon).setOnClickListener(v -> finish());

        findViewById(R.id.view_in_map).setOnClickListener(v -> {
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(foodName + " restaurant"));
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        });
    }
}
