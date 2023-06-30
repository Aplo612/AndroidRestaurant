package com.example.prueba1.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.net.eap.EapSessionConfig;
import android.os.Bundle;
import android.widget.EditText;

import com.example.prueba1.Api.ServiceAPI;
import com.example.prueba1.Conexion.ConnectionREST;
import com.example.prueba1.Models.Usuario;
import com.example.prueba1.R;
import android.view.View;
import android.app.AlertDialog;

import android.provider.ContactsContract;
import android.widget.*;

import android.view.*;

import android.content.*;

import org.w3c.dom.Text;

import kotlinx.coroutines.MainCoroutineDispatcher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {
    private Button BtnSignIn;
    private Button BtnSignUp;
    private EditText EmailText;
    private EditText PasswordText;
    private ServiceAPI serviceAPI;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        serviceAPI = ConnectionREST.getConnection().create(ServiceAPI.class);

        Button BtnSignIn = findViewById(R.id.BSignIn);
        Button BtnSignUp = findViewById(R.id.BSignUp);

        EmailText = (EditText) findViewById(R.id.EmailText);
        PasswordText = (EditText) findViewById(R.id.PasswordText);



        BtnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Usuario obj = new Usuario(2,null,EmailText.getText().toString(),
                        PasswordText.getText().toString(),true);

                IniciarSesion(obj);

            }

        });

        BtnSignUp.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
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

    private void IniciarSesion(Usuario Obj) {
        Call<Usuario> call = serviceAPI.IniciarSesion(Obj);
        call.enqueue(new Callback<Usuario>() {

            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful())
                {
                    Usuario usuario = response.body();
                    mensaje("Se ingreso satisfactoriamente!!!");

                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                    startActivity(intent);

                }
                else
                {
                    mensaje("Intente de nuevo");
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                mensaje("Ocurrio un error!!!" + t.getMessage());
            }
        });
    }

}