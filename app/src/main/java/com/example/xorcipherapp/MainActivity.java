package com.example.xorcipherapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputText;
    private TextView resultText;

    // Native methods for encryption and decryption
    public native String xorEncrypt(String input, int key);
    public native String xorDecrypt(String input, int key);

    // Load the native library
    static {
        System.loadLibrary("native-lib");
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        inputText = findViewById(R.id.inputText);
        resultText = findViewById(R.id.resultText);
        Button encryptButton = findViewById(R.id.encryptButton);
        Button decryptButton = findViewById(R.id.decryptButton);

        // XOR key for encryption/decryption
        int xorKey = 123;

        // Encrypt Button Click Listener
        encryptButton.setOnClickListener(v -> {
            String input = inputText.getText().toString();
            if (!input.isEmpty()) {
                String encryptedText = xorEncrypt(input, xorKey);
                resultText.setText("Encrypted: " + encryptedText);
            } else {
                resultText.setText("Please enter text to encrypt.");
            }
        });

        // Decrypt Button Click Listener
        decryptButton.setOnClickListener(v -> {
            // Extract the encrypted text from the resultText field
            String encryptedText = resultText.getText().toString().replace("Encrypted: ", "");
            if (!encryptedText.isEmpty()) {
                String decryptedText = xorDecrypt(encryptedText, xorKey);
                resultText.setText("Decrypted: " + decryptedText);
            } else {
                resultText.setText("No encrypted text to decrypt.");
            }
        });
    }
}

