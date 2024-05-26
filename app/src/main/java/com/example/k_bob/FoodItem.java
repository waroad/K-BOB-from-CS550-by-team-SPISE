package com.example.k_bob;

public class FoodItem {
    private String name;
    private int imageResId;
    private String description;

    public FoodItem(String name, int imageResId, String description) {
        this.name = name;
        this.imageResId = imageResId;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getDescription() {
        return description;
    }
}
