package com.example.assignment6;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private TextView alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        alert = findViewById(R.id.textAlert);
        EditText username = findViewById(R.id.editTxtUsername);
        EditText password = findViewById(R.id.editTxtPassword);

        Button buttonLogin = findViewById(R.id.btnLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> userData = readUserDataFromFile();

                String inputUsername = username.getText().toString();
                String inputPassword = password.getText().toString();

                if (userData.containsKey(inputUsername) && userData.get(inputUsername).equals(inputPassword)) {
                    alert.setText("Welcome");
                    alert.setTextColor(Color.parseColor("#008000"));
                    Intent gameIntent = new Intent(LoginActivity.this, CalculatorActivity.class);
                    gameIntent.putExtra("username", inputUsername);
                    startActivity(gameIntent);
                } else {
                    alert.setText("The username or password is incorrect");
                    alert.setTextColor(Color.parseColor("#ff0000"));
                }
            }
        });
    }

    private Map<String, String> readUserDataFromFile() {
        Map<String, String> userData = new HashMap<>();
        String fileName = "users.txt";

        try {
            File file = new File(getExternalFilesDir(null), fileName);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Username: ")) {
                        String username = line.substring(10);
                        String email = reader.readLine().substring(7);
                        String password = reader.readLine().substring(10);
                        userData.put(username, password);
                        reader.readLine();
                    }
                }
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userData;
    }
}
