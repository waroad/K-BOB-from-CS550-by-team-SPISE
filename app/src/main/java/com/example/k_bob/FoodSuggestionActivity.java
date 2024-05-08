package com.example.k_bob;// FoodSuggestionActivity.java
import android.os.Bundle;
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
    }
}
