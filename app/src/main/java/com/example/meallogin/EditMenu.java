package com.example.meallogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.meallogin.databinding.ActivityEditMenuBinding;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditMenu extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference dbref= db.getReference();
    ActivityEditMenuBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEditMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Cook cook = (Cook) getIntent().getSerializableExtra("Cook");
        MealAdapter mealAdapter = new MealAdapter(EditMenu.this,cook.getMenu().getOffered(), cook);
        binding.menuTable.setAdapter(mealAdapter);
        binding.menuTable.setClickable(true);
        binding.menuTable.setOnItemClickListener((parent, view, position, id) -> {
            //The page that pops up when you click on an individual meal
            Intent intent = new Intent(EditMenu.this,MealActivity.class);
            intent.putExtra("Name", cook.getMenu().menuNames().get(position));
            intent.putExtra("Price",cook.getMenu().getOffered().get(position).getPrice());
            intent.putExtra("Description", cook.getMenu().getOffered().get(position).getDescription());
            intent.putExtra("Cuisine Type",cook.getMenu().getOffered().get(position).getCuisineType());

            //Turn the Arraylists into Strings
            StringBuffer in = new StringBuffer();
            StringBuffer al = new StringBuffer();
            int n = 0;
            for(String s: cook.getMenu().getOffered().get(position).getIngredients()){
                in.append(s);
                //Append the word
                n++;
                //if not the last word, add a comma and a space
                if(n== cook.getMenu().getOffered().get(position).getIngredients().size()==false){
                    in.append(", ");
                }
            }
            n = 0;
            for(String s:cook.getMenu().getOffered().get(position).getAllergens()){
                al.append(s);
                n++;
                if(n== cook.getMenu().getOffered().get(position).getAllergens().size()==false){
                    in.append(", ");
                }
            }

            intent.putExtra("Ingredients", in.toString());
            intent.putExtra("Allergens", al.toString());
            intent.putExtra("Previous Class", EditMenu.class);
            intent.putExtra("Cook",cook);
            startActivity(intent);
        });
        MaterialButton addMealButton = (MaterialButton) findViewById(R.id.menuAddMealButton);
        MaterialButton removeMealButton = (MaterialButton) findViewById(R.id.menuRemoveMealButton);
        MaterialButton back = (MaterialButton)findViewById(R.id.menuBack);

        addMealButton.setOnClickListener(v1 -> {
            Intent intent = new Intent(this, AddMeal.class);
            intent.putExtra("Cook", cook);
            startActivity(intent);
        });

        removeMealButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), RemoveMeal.class);
            intent.putExtra("Cook", cook);
            startActivity(intent);
        });
        back.setOnClickListener(v2->{
            Intent intent = new Intent(this, WelcomeCookScreen.class);
            intent.putExtra("Cook",cook);
            startActivity(intent);
        });
    }
}