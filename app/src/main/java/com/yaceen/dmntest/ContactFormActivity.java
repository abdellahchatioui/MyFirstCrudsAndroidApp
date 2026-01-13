package com.yaceen.dmntest;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ContactFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact_form);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //ArrayList<String> contact_list = new ArrayList<>();

        EditText nom = findViewById(R.id.name);
        EditText phone = findViewById(R.id.phoneNumber);
        Button sub = findViewById(R.id.submit);
        Button back = findViewById(R.id.btnBack);

        sub.setOnClickListener(v -> {
            String name = nom.getText().toString().trim();
            String phonenum = phone.getText().toString().trim();

            //Contact newContact = new Contact(name, phonenum);
            // DataBase.contactList.add(newContact);

            SQLiteDatabase db = DatabaseHelper.getInstance(this).getWritableDatabase();
            db.execSQL("INSERT INTO contacts(nom,phone) VALUES(?,?)", new String[]{name,phonenum});

            Toast.makeText(this,"Bien ajouter "+name, Toast.LENGTH_LONG).show();

            Intent goBackToList = new  Intent(ContactFormActivity.this,ContactActivity.class);
            startActivity(goBackToList);
        });

        back.setOnClickListener((v)->{
            Intent goBackToHome = new Intent(this, ContactActivity.class);
            startActivity(goBackToHome);
        });
    }
}