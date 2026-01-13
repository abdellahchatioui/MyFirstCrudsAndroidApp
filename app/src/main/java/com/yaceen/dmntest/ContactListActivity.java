package com.yaceen.dmntest;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import java.util.ArrayList;

public class ContactListActivity extends AppCompatActivity {

    // UI components
    private ListView listView;
    private Button btnBack;

    // Data
    private ArrayList<Contact> contactList;
    private ArrayAdapter<Contact> contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        listView = findViewById(R.id.lvContact);
        btnBack = findViewById(R.id.btnBack);

        contactList = new ArrayList<>();
        contactAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                contactList
        );

        listView.setAdapter(contactAdapter);

        btnBack.setOnClickListener(v -> {
            Intent goToHome = new Intent(this, ContactActivity.class);
            startActivity(goToHome);
        });


        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(ContactListActivity.this, ItemDetails.class);
            intent.putExtra("contact_id", contactList.get(position).getId());
            startActivity(intent);
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        loadContacts();
    }

    private void loadContacts() {
        contactList.clear();

        SQLiteDatabase db = DatabaseHelper.getInstance(this).getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, nom, phone FROM contacts", null);

            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("nom"));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"));

            contactList.add(new Contact(id, name, phone));
        }

        cursor.close();
        contactAdapter.notifyDataSetChanged();
    }
}
