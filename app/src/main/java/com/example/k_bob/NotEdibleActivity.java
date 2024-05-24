package com.example.k_bob;

import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NotEdibleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edible);

        // Retrieve the strings from the intent
        String stringA = getIntent().getStringExtra("stringA");
        String stringB = getIntent().getStringExtra("stringB");
        String stringC = getIntent().getStringExtra("stringC");

        // Find the TextViews and set the strings
        TextView textViewA = findViewById(R.id.text_view_a);
        TextView textViewB = findViewById(R.id.text_view_b);
        TextView textViewC = findViewById(R.id.text_view_c);

        textViewA.setText(stringA);
        textViewB.setText(stringB);
        textViewC.setText(stringC);
    }
}
