package com.example.k_bob;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

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

        ImageView backIcon = findViewById(R.id.back_icon);
        backIcon.setOnClickListener(v -> finish());

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        List<FoodItem> foodItemList = new ArrayList<>();
        for (String foodName : foodNames) {
            int imageResId = getFoodImageResourceId(foodName);
            foodItemList.add(new FoodItem(foodName, imageResId));
        }

        FoodListAdapter adapter = new FoodListAdapter(foodItemList);
        recyclerView.setAdapter(adapter);
    }

    private int getFoodImageResourceId(String foodName) {
        // Replace with actual logic to get image resource ID based on food name
        switch (foodName) {
            case "Pizza":
                return R.drawable.pizza;
            default:
                return R.drawable.default_food; // default image if no match is found
        }
    }
}
