package com.yaceen.dmntest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        DataBase.initData();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button login = findViewById(R.id.login);

        login.setOnClickListener( v -> {
            EditText usernameEditText = findViewById(R.id.username);
            String username = usernameEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, ContactActivity.class);
            intent.putExtra("USER", username);
            startActivity(intent);
            Toast.makeText(this, "btn OK Clicked", Toast.LENGTH_LONG).show();
        });
    }
}