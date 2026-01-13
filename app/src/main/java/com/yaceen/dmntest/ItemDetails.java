package com.yaceen.dmntest;

import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
        Intent data = getIntent();
        int contact_id = data.getIntExtra("contact_id",-1);

        SQLiteDatabase db = DatabaseHelper.getInstance(this).getReadableDatabase();

        Cursor cursor = db.rawQuery(
                        "SELECT id,nom,phone FROM contacts WHERE id = ?",
                         new String[]{String.valueOf(contact_id)});

        if(cursor.moveToFirst()){
            String name = cursor.getString(cursor.getColumnIndexOrThrow("nom"));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"));

            txtItemName.setText(name);
            txtItemNumber.setText(phone);
        }
        /*
        Contact removedItemName = DataBase.contactList.get(itemID);
        txtItemName.setText(removedItemName.getName());
        txtItemNumber.setText(removedItemName.getPhone());
        */
        Button btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener((v -> {
            // DataBase.contactList.remove(itemID);
            new AlertDialog.Builder(this)
                    .setTitle("Delete")
                    .setMessage("Delete Contact")
                    .setPositiveButton("Delete", (d,w) -> {
                        SQLiteDatabase dbDelete = DatabaseHelper.getInstance(this).getWritableDatabase();
                        dbDelete.delete("contacts","id=?",new String[]{String.valueOf(contact_id)});
                        Toast.makeText(getApplicationContext(), txtItemName.getText() + " is removed!", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .setNegativeButton("Cancel",null)
                    .show();
        }));

        Button btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener((v -> {
            String newName = txtItemName.getText().toString().trim();
            String newPhone = txtItemNumber.getText().toString().trim();

            new AlertDialog.Builder(this)
                    .setTitle("Update")
                    .setMessage("Update Contact")
                    .setPositiveButton("Update", (n,w) -> {
                        ContentValues values = new ContentValues();
                        values.put("nom",newName);
                        values.put("phone",newPhone);

                        // get Contact by Id
                        SQLiteDatabase dbUpdate = DatabaseHelper.getInstance(this).getWritableDatabase();
                        dbUpdate.update("contacts",values,"id=?",new String[]{String.valueOf(contact_id)});

                        Toast.makeText(this, "Contact Updated", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .setNegativeButton("Cancel",null)
                    .show();
        }));

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener((v -> {
            Intent goBack = new Intent(ItemDetails.this, ContactListActivity.class);
            startActivity(goBack);
        }));



    }
}