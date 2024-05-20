package com.example.k_bob;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class FoodSuggestionActivity extends AppCompatActivity {
    private static final String[] foodNames = {
            "Pizza", "Burger", "Sushi", "Pasta", "Salad", "Ramen", "Tacos", "Steak", "Falafel",
            "Curry", "Dumplings", "Paella", "Burrito", "Bagel", "Pho", "Shawarma", "Samosa",
            "Moussaka", "Risotto", "Noodles"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_suggestion);

        // Initialize the ListView with an adapter
        ListView foodListView = findViewById(R.id.list_food_suggestions);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, foodNames);
        foodListView.setAdapter(adapter);

        // Set an item click listener to navigate to the food information screen
        foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedFood = foodNames[position];
                Intent intent = new Intent(FoodSuggestionActivity.this, FoodInformationActivity.class);
                intent.putExtra("foodName", selectedFood);
                // Add corresponding image resources if needed
                startActivity(intent);
            }
        });
    }
}
