package com.example.prueba1.ui.editar_platos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import android.view.View;

import com.example.prueba1.Activities.MainActivity;
import com.example.prueba1.Activities.SignInActivity;
import com.example.prueba1.Api.ServiceAPI;
import com.example.prueba1.Conexion.ConnectionREST;
import com.example.prueba1.Models.DetallePedidos;
import com.example.prueba1.Models.Plato;
import com.example.prueba1.Models.Usuario;
import com.example.prueba1.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditarPlatosActivity extends AppCompatActivity {

    private EditText etNombre;
    private EditText etCodigo;
    private EditText etPrecio;
    private EditText etDescripcion;
    private Switch switch2;
    private ServiceAPI serviceAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_platos);

        serviceAPI = ConnectionREST.getConnection().create(ServiceAPI.class);

        Button BtnModificar = findViewById(R.id.btnModificar);
        Button BtnEliminar = findViewById(R.id.btnEliminar);
        Button BtnProcesar = findViewById(R.id.btnProcesar);
        Button BtnMostrar = findViewById(R.id.btnMostrar);

        etNombre = (EditText) findViewById(R.id.etNombre);
        etCodigo = (EditText) findViewById(R.id.etCodigo);
        etPrecio = (EditText) findViewById(R.id.etPrecio);
        etDescripcion = (EditText) findViewById(R.id.etDescripcion);
        switch2 = (Switch) findViewById(R.id.switch2);

        BtnModificar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                    Plato obj = new Plato(Integer.parseInt(etCodigo.getText().toString()), etNombre.getText().toString(),
                            etDescripcion.getText().toString(),
                            Integer.parseInt(etPrecio.getText().toString()),
                            switch2.isChecked(), null);

                    editarPlatos(obj);
            }

        });

        BtnEliminar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                int codElimanar = Integer.parseInt(etCodigo.getText().toString());

                removePlatos(codElimanar);
            }

        });

        BtnProcesar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if(etCodigo.getText().toString().isEmpty()){

                    Plato obj = new Plato(0, etNombre.getText().toString(),
                            etDescripcion.getText().toString(),
                            Integer.parseInt(etPrecio.getText().toString()),
                            switch2.isChecked(), null);
                    addPlato(obj);
                }
                else {
                    mensaje("El 'id' debe estar vacío");
                }

            }

        });

        BtnMostrar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent intent = new Intent(EditarPlatosActivity.this, MostrarEPlatosActivity.class);
                startActivity(intent);

            }

        });

    }

    public void mensaje(String msg)
    {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setMessage(msg);
        alerta.show();
    }

    public void addPlato(Plato obj)
    {
        Call<Plato> call = serviceAPI.AdicionarPlatos(obj);
        call.enqueue(new Callback<Plato>() {
            @Override
            public void onResponse(Call<Plato> call, Response<Plato> response) {
                if(response.isSuccessful())
                {
                    Plato pro = response.body();
                    mensaje("Plato grabado satisfactoriamente!");
                }
                else
                {
                    mensaje("Ocurrio un error al grabar los datos!");
                }
            }
            @Override
            public void onFailure(Call<Plato> call, Throwable t) {
                mensaje("Ocurrio un error desconocido!" + t.getMessage());
            }
        });
    }

    public void removePlatos(int id)
    {
        Call<Plato> call = serviceAPI.eliminarPlatos(id);
        call.enqueue(new Callback<Plato>() {
            @Override
            public void onResponse(Call<Plato> call, Response<Plato> response) {
                if(response.isSuccessful())
                {
                    Plato pro = response.body();
                    mensaje("Plato eliminado satisfactoriamente!");
                }
                else
                {
                    mensaje("Ocurrio un error al eliminar los datos!");
                }
            }
            @Override
            public void onFailure(Call<Plato> call, Throwable t) {
                mensaje("Ocurrio un error desconocido!" + t.getMessage());
            }
        });
    }

    public void editarPlatos(Plato obj)
    {
        Call<Plato> call = serviceAPI.editarDetallesPlatos(obj);
        call.enqueue(new Callback<Plato>() {
            @Override
            public void onResponse(Call<Plato> call, Response<Plato> response) {
                if(response.isSuccessful())
                {
                    Plato pro = response.body();
                    mensaje("Plato modiicado satisfactoriamente!");
                }
                else
                {
                    mensaje("Ocurrio un error al modificar los datos!");
                }
            }
            @Override
            public void onFailure(Call<Plato> call, Throwable t) {
                mensaje("Ocurrio un error desconocido!" + t.getMessage());
            }
        });
    }





}