package com.example.prueba1.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.prueba1.Api.ServiceAPI;
import com.example.prueba1.Conexion.ConnectionREST;
import com.example.prueba1.Models.Usuario;
import com.example.prueba1.R;
import android.widget.*;
import android.content.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private EditText editTextText2;
    private EditText editTextText3;
    public EditText editTextTextEmailAddress;
    private EditText editTextTextPassword;
    private EditText editTextTextPassword3;
    private Switch switch1;


    private ServiceAPI serviceAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        serviceAPI = ConnectionREST.getConnection().create(ServiceAPI.class);
        setContentView(R.layout.activity_sign_up);

        Button button = findViewById(R.id.BSingUp2);

        editTextText2 = (EditText) findViewById(R.id.editTextText2);
        editTextText3 = (EditText) findViewById(R.id.editTextText3);
        editTextTextEmailAddress = (EditText) findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword = (EditText) findViewById(R.id.editTextTextPassword);
        editTextTextPassword3 = (EditText) findViewById(R.id.editTextTextPassword3);
        switch1 = (Switch) findViewById(R.id.switch1);

        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if(editTextText2.getText().toString().trim().isEmpty() || editTextText3.getText().toString().trim().isEmpty()
                        || editTextTextEmailAddress.getText().toString().trim().isEmpty() || editTextTextPassword.getText().toString().trim().isEmpty()
                        || editTextTextPassword3.getText().toString().trim().isEmpty()) {

                    mensaje("Llene todos los espacios en blanco");
                }
                else if(editTextTextPassword.getText().toString().trim().equals(editTextTextPassword3.getText().toString().trim())) {

                    Usuario usuario = new Usuario(0, editTextText2.getText().toString().trim() +
                            editTextText3.getText().toString().trim(),
                            editTextTextEmailAddress.getText().toString().trim(),
                            editTextTextPassword.getText().toString().trim(), switch1.isChecked());
                    Registrarse(usuario);
                }
                else
                {
                    mensaje("La contraseña no coincide");
                }
            }
        });
    }

    private void Registrarse(Usuario obj){
        Call<Usuario> call = serviceAPI.Registrarse(obj);
        call.enqueue(new Callback<Usuario>() {

            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful())
                {
                    Usuario usuario = response.body();
                    //mensaje("Se registró satisfactoriamente!!!");

                    Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                    startActivity(intent);
                }
                else
                {
                    mensaje("Ocurrio un error!!!");
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                mensaje("Ocurrio un error!!!" + t.getMessage());
            }
        });
    }

    public void mensaje(String msg)
    {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setMessage(msg);
        alerta.show();
    }
}