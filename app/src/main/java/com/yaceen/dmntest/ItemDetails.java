package com.yaceen.dmntest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ItemDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_item_details);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView txtItemName = findViewById(R.id.txtItemName);
        TextView txtItemNumber = findViewById(R.id.txtItemNumber);
        Button btnDelete = findViewById(R.id.btnDelete);

        Intent data = getIntent();
        int position = data.getIntExtra("itemPosition",-1);
        Contact removedItemName = DataBase.contactList.get(position);
        txtItemName.setText(removedItemName.getName());
        txtItemNumber.setText(removedItemName.getPhone());

        btnDelete.setOnClickListener((v -> {
            DataBase.contactList.remove(position);
            Toast.makeText(getApplicationContext(), removedItemName + " is removed!", Toast.LENGTH_SHORT).show();
            Intent goBackToListItems = new Intent(this,ContactListActivity.class);
            startActivity(goBackToListItems);
            finish();
        }));

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener((v -> {
            Intent goBack = new Intent(ItemDetails.this, ContactListActivity.class);
            startActivity(goBack);
        }));



    }
}