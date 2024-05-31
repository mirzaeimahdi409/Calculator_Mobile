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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SignUpActivity extends AppCompatActivity {

    private TextView alert;
    EditText username, email, password, enterPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        alert = findViewById(R.id.textAlert);
        username = findViewById(R.id.editTxtUsername);
        email = findViewById(R.id.editTxtEmail);
        password = findViewById(R.id.editTxtPassword);
        enterPassword = findViewById(R.id.editTxtEnterPassword);

        Button buttonSignUp = findViewById(R.id.btnSignUp);


        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().isEmpty()) {
                    alert.setText("Username is required");
                    alert.setTextColor(Color.parseColor("#ff0000"));
                } else if (email.getText().toString().isEmpty()) {
                    alert.setText("Email is required");
                    alert.setTextColor(Color.parseColor("#ff0000"));
                } else if (password.getText().toString().isEmpty()) {
                    alert.setText("Password is required");
                    alert.setTextColor(Color.parseColor("#ff0000"));
                } else if (enterPassword.getText().toString().isEmpty()) {
                    alert.setText("Enter Password is required");
                    alert.setTextColor(Color.parseColor("#ff0000"));
                } else checker();
            }
        });
    }

    private void checker() {
        if (username.getText().length() < 5) {
            alert.setText("Username must be longer than 5 characters");
            alert.setTextColor(Color.parseColor("#ff0000"));
        } else if (!email.getText().toString().matches("^.+@.+\\..+$")) {
            alert.setText("email not valid.");
            alert.setTextColor(Color.parseColor("#ff0000"));
        } else if (password.getText().length() < 8) {
            alert.setText("password must be longer than 8 characters");
            alert.setTextColor(Color.parseColor("#ff0000"));
        } else if (!password.getText().toString().equals(enterPassword.getText().toString())) {
            alert.setText("Password is not the same as confirm password");
            alert.setTextColor(Color.parseColor("#ff0000"));
        } else {
            alert.setText("You have successfully registered.");
            alert.setTextColor(Color.parseColor("#008000"));
            saveToFile(username.getText().toString(), email.getText().toString(), password.getText().toString());

            Intent gameIntent = new Intent(SignUpActivity.this, CalculatorActivity.class);
            gameIntent.putExtra("username", username.getText().toString());
            startActivity(gameIntent);
        }
    }

    private void saveToFile(String username, String email, String password) {
        String fileName = "users.txt";
        String content = "Username: " + username + "\nEmail: " + email + "\nPassword: " + password + "\n\n";

        FileOutputStream fos = null;
        try {
            File file = new File(getExternalFilesDir(null), fileName);
            fos = new FileOutputStream(file, true);
            fos.write(content.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}