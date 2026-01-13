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

import java.util.ArrayList;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        String username = intent.getStringExtra("USER");

        TextView welcomeTxtView = findViewById(R.id.welcome);
        welcomeTxtView.setText("Welcome " + username);

        Button logout = findViewById(R.id.logout);
        logout.setOnClickListener(v -> {
            Intent goback = new Intent(ContactActivity.this, MainActivity.class);
            startActivity(goback);
            finish();
        });

        Button display = findViewById(R.id.btndisplay);
        display.setOnClickListener(v -> {
            Intent displayList = new Intent(ContactActivity.this, ContactListActivity.class);
            startActivity(displayList);
        });

        Button add = findViewById(R.id.btnAdd);
        add.setOnClickListener(v -> {
            Intent addContact = new Intent(ContactActivity.this, ContactFormActivity.class);
            startActivity(addContact);
        });


    }
}