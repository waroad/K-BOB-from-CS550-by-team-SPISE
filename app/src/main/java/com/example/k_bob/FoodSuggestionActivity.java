package com.example.k_bob;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class FoodSuggestionActivity extends AppCompatActivity {

    private static final String[] foodNames = {
            "Pizza", "Burger", "Sushi", "Pasta", "Salad", "Ramen", "Tacos", "Steak", "Falafel",
            "Curry", "Dumplings", "Paella", "Burrito", "Bagel", "Pho", "Shawarma", "Samosa",
            "Moussaka", "Risotto", "Noodles"
    };

    private List<FoodItem> foodItemList = new ArrayList<>();
    private FoodListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_suggestion);

        ImageView backIcon = findViewById(R.id.back_icon);
        backIcon.setOnClickListener(v -> finish());

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        for (String foodName : foodNames) {
            int imageResId = getFoodImageResourceId(foodName);
            foodItemList.add(new FoodItem(foodName, imageResId, "Brief description of " + foodName));
        }

        adapter = new FoodListAdapter(foodItemList, this::showDeleteConfirmationDialog);
        recyclerView.setAdapter(adapter);
    }

    private int getFoodImageResourceId(String foodName) {
        switch (foodName) {
            case "Pizza":
                return R.drawable.pizza;
            default:
                return R.drawable.default_food;
        }
    }

    private void showDeleteConfirmationDialog(FoodItem foodItem) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Food")
                .setMessage("Are you sure you want to delete " + foodItem.getName() + "?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    foodItemList.remove(foodItem);
                    adapter.notifyDataSetChanged();
                })
                .setNegativeButton("No", null)
                .show();
    }
}
