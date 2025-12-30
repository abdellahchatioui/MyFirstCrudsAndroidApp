package com.yaceen.dmntest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ContactListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView listView = findViewById(R.id.lvContact);
        // String[] items = {"item 1", "item 2", "item 3"};

        ArrayAdapter<Contact> contactAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, DataBase.contactList);
        listView.setAdapter(contactAdapter);

        listView.setOnItemClickListener(( parent,view,  position, id) -> {
            Intent toItemDetails = new Intent(ContactListActivity.this, ItemDetails.class);
            toItemDetails.putExtra("itemPosition",position);
            startActivity(toItemDetails);
            // Toast.makeText(ContactListActivity.this, "Clicked: " + DataBase.contactList.get(position), 0).show();
        });

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener((v -> {
            Intent goBack = new Intent(ContactListActivity.this, ContactActivity.class);
            startActivity(goBack);
        }));


    }
}