package com.example.danilova;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.danilova.databinding.ActivitySignupBinding;
import com.example.danilova.placeholder.DatabaseHelper;
import com.example.danilova.placeholder.LoginActivity;

public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DatabaseHelper(this);
        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String users = binding.signupPeople.getText().toString();
                String password = binding.signupPassword.getText().toString();
                String confirmPassword = binding.signupConfirm.getText().toString();

                if (users.equals("") || password.equals("") || confirmPassword.equals(""))
                    Toast.makeText(SignupActivity.this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                else {
                    if (password.equals(confirmPassword)) {
                        Boolean checkUserPeople= databaseHelper.cheackUsers(users);

                        if (checkUserPeople == false) {
                            boolean insert = databaseHelper.insertData(users, password);

                            if (insert == true) {
                                Toast.makeText(SignupActivity.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignupActivity.this, "Registro Fallido", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SignupActivity.this, "El usuario ya existe, Por favor iniciar Sesion", Toast.LENGTH_SHORT).show();


                        }
                    }else {
                        Toast.makeText(SignupActivity.this, "Contraseña Invalida", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        binding.loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
            }
        }
