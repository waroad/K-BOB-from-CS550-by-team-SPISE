package com.example.k_bob;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.ViewHolder> {

    private List<FoodItem> foodItems;

    public FoodListAdapter(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodItem foodItem = foodItems.get(position);
        holder.foodName.setText(foodItem.getName());
        holder.foodImage.setImageResource(foodItem.getImageResId());
        holder.foodInfoButton.setOnClickListener(v -> {
            Context context = v.getContext();
            Intent intent = new Intent(context, FoodInformationActivity.class);
            intent.putExtra("foodName", foodItem.getName());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView foodName;
        public ImageView foodImage;
        public Button foodInfoButton;

        public ViewHolder(View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.food_name);
            foodImage = itemView.findViewById(R.id.food_image);
            foodInfoButton = itemView.findViewById(R.id.food_info_button);
        }
    }
}
