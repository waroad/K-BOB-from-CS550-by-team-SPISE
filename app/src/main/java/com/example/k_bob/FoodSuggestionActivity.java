package com.example.k_bob;

import android.content.DialogInterface;
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

        populateFoodItems();

        adapter = new FoodListAdapter(foodItemList, this::showDeleteConfirmationDialog);
        recyclerView.setAdapter(adapter);
    }

    private void populateFoodItems() {
        foodItemList.add(new FoodItem("Bibimbap", R.drawable.bibimbap, "Mixed rice with vegetables", "#rice #vegetables #gochujang", "A mixed rice dish with various seasoned vegetables, often topped with gochujang, and sometimes tofu or mushrooms.", "Rice, spinach, carrots, zucchini, mushrooms, bean sprouts, gochujang, sesame oil, nori, tofu", "Vegan, Lacto, Ovo, Lacto-ovo", "Contains sesame, soy. Free from beef, pork, chicken, lamb, shellfish, crustaceans, dairy, eggs, peanuts, wheat, corn, gluten, mustard, celery, sulfites, lupin."));
        foodItemList.add(new FoodItem("Japchae", R.drawable.japchae, "Stir-fried glass noodles with vegetables", "#sweet potato noodles #spinach #mushrooms", "Sweet potato glass noodles stir-fried with a variety of vegetables, often served with soy sauce and sesame oil.", "Sweet potato noodles, spinach, carrots, mushrooms, bell peppers, onions, garlic, soy sauce, sesame oil, scallions", "Vegan, Lacto, Ovo, Lacto-ovo", "Contains sesame, soy. Free from beef, pork, chicken, lamb, shellfish, crustaceans, dairy, eggs, peanuts, wheat, corn, gluten, mustard, celery, sulfites, lupin."));
        foodItemList.add(new FoodItem("Kongguksu", R.drawable.kongguksu, "Cold soy milk noodle soup", "#soy milk #noodles #cucumber", "A refreshing cold noodle soup made with soy milk, typically garnished with cucumber and sometimes sesame seeds.", "Soy milk, wheat noodles, cucumber, sesame seeds, salt, soy sauce, scallions, garlic, vinegar, ice cubes", "Vegan, Lacto, Ovo, Lacto-ovo", "Contains soy, wheat, sesame. Free from beef, pork, chicken, lamb, shellfish, crustaceans, dairy, eggs, peanuts, corn, gluten, mustard, celery, sulfites, lupin."));
        foodItemList.add(new FoodItem("Doenjang Jjigae", R.drawable.doenjang_jjigae, "Soybean paste stew with tofu", "#doenjang #tofu #zucchini", "A hearty stew made with fermented soybean paste (doenjang), tofu, and assorted vegetables.", "Doenjang, tofu, zucchini, potatoes, onions, mushrooms, garlic, scallions, chili peppers, water", "Vegan, Lacto, Ovo, Lacto-ovo", "Contains soy. Free from beef, pork, chicken, lamb, shellfish, crustaceans, dairy, eggs, peanuts, wheat, corn, gluten, mustard, celery, sulfites, lupin."));
        foodItemList.add(new FoodItem("Sundubu Jjigae", R.drawable.sundubu_jjigae, "Spicy soft tofu stew", "#soft tofu #gochujang #vegetables", "A spicy stew made with soft tofu, vegetables, and seasoned with gochujang and gochugaru.", "Soft tofu, zucchini, mushrooms, onions, garlic, gochujang, gochugaru, scallions, soy sauce, vegetable broth", "Vegan, Lacto, Ovo, Lacto-ovo", "Contains soy. Free from beef, pork, chicken, lamb, shellfish, crustaceans, dairy, eggs, peanuts, wheat, corn, gluten, mustard, celery, sulfites, lupin."));
        foodItemList.add(new FoodItem("Kimchi Bokkeumbap", R.drawable.kimchi_bokkeumbap, "Kimchi fried rice", "#rice #kimchi #vegetables", "A flavorful fried rice dish made with kimchi, vegetables, and often tofu or mushrooms.", "Rice, vegan kimchi, carrots, onions, peas, corn, tofu, garlic, soy sauce, sesame oil", "Vegan, Lacto, Ovo, Lacto-ovo", "Contains soy, sesame. Free from beef, pork, chicken, lamb, shellfish, crustaceans, dairy, eggs, peanuts, wheat, corn, gluten, mustard, celery, sulfites, lupin."));
        foodItemList.add(new FoodItem("Yachae Kimbap", R.drawable.yachae_kimbap, "Vegetable rice rolls", "#rice #vegetables #nori", "Rice and assorted vegetables rolled in nori seaweed, similar to sushi but with a Korean twist.", "Rice, carrots, spinach, cucumber, pickled radish, nori, sesame oil, soy sauce, garlic, salt", "Vegan, Lacto, Ovo, Lacto-ovo", "Contains sesame, soy. Free from beef, pork, chicken, lamb, shellfish, crustaceans, dairy, eggs, peanuts, wheat, corn, gluten, mustard, celery, sulfites, lupin."));
        foodItemList.add(new FoodItem("Tofu Jorim", R.drawable.tofu_jorim, "Braised tofu", "#tofu #soy sauce #garlic", "Tofu braised in a savory soy-based sauce with garlic and chili.", "Tofu, soy sauce, garlic, onions, scallions, sesame oil, chili flakes, sugar, water, sesame seeds", "Vegan, Lacto, Ovo, Lacto-ovo", "Contains soy, sesame. Free from beef, pork, chicken, lamb, shellfish, crustaceans, dairy, eggs, peanuts, wheat, corn, gluten, mustard, celery, sulfites, lupin."));
        foodItemList.add(new FoodItem("Hobakjuk", R.drawable.hobakjuk, "Sweet pumpkin porridge", "#pumpkin #rice #sugar", "A creamy and sweet porridge made with pumpkin and rice, often served as a dessert or snack.", "Pumpkin, rice, sugar, water, salt, pine nuts, red beans, chestnuts, jujubes, honey", "Vegan, Lacto, Ovo, Lacto-ovo", "Contains pine nuts. Free from beef, pork, chicken, lamb, shellfish, crustaceans, dairy, eggs, peanuts, wheat, soy, corn, gluten, mustard, celery, sulfites, lupin."));
        foodItemList.add(new FoodItem("Buchimgae", R.drawable.buchimgae, "Vegetable pancakes", "#flour #vegetables #water", "Savory pancakes made with a batter of flour and water, mixed with various vegetables.", "Flour, water, zucchini, carrots, onions, scallions, garlic, salt, chili peppers, dipping sauce", "Vegan", "Contains wheat, gluten. Free from beef, pork, chicken, lamb, shellfish, crustaceans, dairy, eggs, peanuts, soy, corn, mustard, celery, sulfites, lupin."));
        foodItemList.add(new FoodItem("Dubu Kimchi", R.drawable.dubu_kimchi, "Stir-fried kimchi with tofu", "#kimchi #tofu #garlic", "Stir-fried kimchi served with sliced tofu, often enjoyed as a side dish or main dish.", "Vegan kimchi, tofu, garlic, onions, scallions, sesame oil, soy sauce, sugar, chili flakes, water", "Vegan, Lacto, Ovo, Lacto-ovo", "Contains soy, sesame. Free from beef, pork, chicken, lamb, shellfish, crustaceans, dairy, eggs, peanuts, wheat, corn, gluten, mustard, celery, sulfites, lupin."));
        foodItemList.add(new FoodItem("Nokdujeon", R.drawable.nokdujeon, "Mung bean pancakes", "#mung beans #kimchi #vegetables", "Savory pancakes made from ground mung beans mixed with vegetables and kimchi.", "Mung beans, vegan kimchi, onions, scallions, garlic, carrots, bean sprouts, salt, sesame oil, water", "Vegan", "Contains sesame, soy. Free from beef, pork, chicken, lamb, shellfish, crustaceans, dairy, eggs, peanuts, wheat, corn, gluten, mustard, celery, sulfites, lupin."));
        foodItemList.add(new FoodItem("Tteokbokki", R.drawable.tteokbokki, "Spicy rice cakes", "#rice cakes #gochujang #vegetables", "Chewy rice cakes cooked in a spicy and sweet gochujang sauce with vegetables.", "Rice cakes, gochujang, carrots, cabbage, onions, scallions, garlic, soy sauce, sugar, vegetable broth", "Vegan, Lacto, Ovo, Lacto-ovo", "Contains soy. Free from beef, pork, chicken, lamb, shellfish, crustaceans, dairy, eggs, peanuts, wheat, corn, gluten, mustard, celery, sulfites, lupin."));
        foodItemList.add(new FoodItem("Ssambap", R.drawable.ssambap, "Rice and vegetables wrapped in leafy greens", "#rice #leafy greens #ssamjang", "Rice and vegetables wrapped in leafy greens, typically served with a spicy ssamjang paste.", "Rice, lettuce, perilla leaves, carrots, cucumbers, bell peppers, ssamjang, garlic, sesame oil, chili paste", "Vegan, Lacto, Ovo, Lacto-ovo", "Contains soy, sesame. Free from beef, pork, chicken, lamb, shellfish, crustaceans, dairy, eggs, peanuts, wheat, corn, gluten, mustard, celery, sulfites, lupin."));
        foodItemList.add(new FoodItem("Kimchi Jjigae", R.drawable.kimchi_jjigae, "Kimchi stew with tofu", "#kimchi #tofu #vegetables", "A hearty and spicy stew made with kimchi, tofu, and various vegetables.", "Vegan kimchi, tofu, onions, garlic, scallions, zucchini, mushrooms, gochujang, soy sauce, vegetable broth", "Vegan, Lacto, Ovo, Lacto-ovo", "Contains soy. Free from beef, pork, chicken, lamb, shellfish, crustaceans, dairy, eggs, peanuts, wheat, corn, gluten, sesame, mustard, celery, sulfites, lupin."));
        foodItemList.add(new FoodItem("Gaji Bokkeum", R.drawable.gaji_bokkeum, "Stir-fried eggplant", "#eggplant #soy sauce #garlic", "Stir-fried eggplant seasoned with soy sauce, garlic, and sesame oil.", "Eggplant, soy sauce, garlic, onions, scallions, sesame oil, sesame seeds, chili flakes, sugar, water", "Vegan, Lacto, Ovo, Lacto-ovo", "Contains soy, sesame. Free from beef, pork, chicken, lamb, shellfish, crustaceans, dairy, eggs, peanuts, wheat, corn, gluten, mustard, celery, sulfites, lupin."));
        foodItemList.add(new FoodItem("Yachae Bibim Guksu", R.drawable.yachae_bibim_guksu, "Spicy mixed noodles with vegetables", "#noodles #vegetables #gochujang", "Cold noodles mixed with a variety of vegetables and a spicy gochujang sauce.", "Wheat noodles, cucumber, carrots, spinach, gochujang, soy sauce, garlic, sesame oil, sugar, vinegar", "Vegan, Lacto, Ovo, Lacto-ovo", "Contains soy, wheat, sesame. Free from beef, pork, chicken, lamb, shellfish, crustaceans, dairy, eggs, peanuts, corn, gluten, mustard, celery, sulfites, lupin."));
        foodItemList.add(new FoodItem("Kongnamul Gukbap", R.drawable.kongnamul_gukbap, "Bean sprout soup with rice", "#bean sprouts #rice #garlic", "A comforting soup made with bean sprouts, rice, and seasoned with garlic and soy sauce.", "Bean sprouts, rice, garlic, scallions, soy sauce, sesame oil, gochugaru, vegetable broth, salt, pepper", "Vegan, Lacto, Ovo, Lacto-ovo", "Contains soy, sesame. Free from beef, pork, chicken, lamb, shellfish, crustaceans, dairy, eggs, peanuts, wheat, corn, gluten, mustard, celery, sulfites, lupin."));
        foodItemList.add(new FoodItem("Sigeumchi Doenjang Guk", R.drawable.sigeumchi_doenjang_guk, "Spinach and soybean paste soup", "#spinach #doenjang #garlic", "A savory soup made with spinach, soybean paste (doenjang), and garlic.", "Spinach, doenjang, garlic, onions, scallions, tofu, water, soy sauce, sesame oil, salt", "Vegan, Lacto, Ovo, Lacto-ovo", "Contains soy, sesame. Free from beef, pork, chicken, lamb, shellfish, crustaceans, dairy, eggs, peanuts, wheat, corn, gluten, mustard, celery, sulfites, lupin."));
        foodItemList.add(new FoodItem("Chwinamul Bap", R.drawable.chwinamul_bap, "Steamed rice with seasoned aster leaves", "#rice #aster leaves #garlic", "Steamed rice mixed with seasoned aster leaves and garlic, often enjoyed with a soy-based dipping sauce.", "Rice, aster leaves, garlic, soy sauce, sesame oil, scallions, chili flakes, salt, water, vegetable broth", "Vegan, Lacto, Ovo, Lacto-ovo", "Contains soy, sesame. Free from beef, pork, chicken, lamb, shellfish, crustaceans, dairy, eggs, peanuts, wheat, corn, gluten, mustard, celery, sulfites, lupin."));
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
