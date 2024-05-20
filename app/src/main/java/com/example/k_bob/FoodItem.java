package com.example.k_bob;

public class FoodItem {
    private String name;
    private int imageResId;

    public FoodItem(String name, int imageResId) {
        this.name = name;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }
}
