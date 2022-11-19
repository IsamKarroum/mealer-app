package com.example.meallogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddMeal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);
        Cook cook = (Cook) getIntent().getSerializableExtra("Cook");

        MaterialButton add = (MaterialButton) findViewById(R.id.addButton);
        add.setOnClickListener(v -> {
            String mealName = ((EditText) findViewById(R.id.toAddTextInput)).getText().toString();
            boolean exists = false;
            int i=0;
            while (i<cook.getMealListSize() && exists == false) {
                if (cook.getMealListNames().get(i).equals(mealName)) {
                    exists = true;
                }
                i++;
            }
            if (exists) {
                cook.getMenu().addtoOffered(cook.getMealByName(mealName));
            } else {
                Toast.makeText(getApplicationContext(), "Failed. Please try again.", Toast.LENGTH_LONG).show();
            }

            Toast.makeText(getApplicationContext(), "Meal added successfully", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, WelcomeCookScreen.class);
            startActivity(intent);
        });
    }


}