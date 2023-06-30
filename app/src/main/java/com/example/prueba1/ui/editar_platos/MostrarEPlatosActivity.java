package com.example.prueba1.ui.editar_platos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prueba1.Api.ServiceAPI;
import com.example.prueba1.Conexion.ConnectionREST;
import com.example.prueba1.Models.Plato;
import com.example.prueba1.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MostrarEPlatosActivity extends AppCompatActivity {

    private ServiceAPI serviceAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_eplatos);

        serviceAPI = ConnectionREST.getConnection().create(ServiceAPI.class);

        load();
    }

    public void load() {
        Call<List<Plato>> call = serviceAPI.GetPlatos();

        call.enqueue(new Callback<List<Plato>>() {
            @Override
            public void onResponse(Call<List<Plato>> call, Response<List<Plato>> response) {
                if (response.isSuccessful()) {
                    List<Plato> respuesta = response.body();

                    LinearLayout cardDeck = findViewById(R.id.cardDeck);
                    cardDeck.removeAllViews(); // Limpiar cualquier vista existente

                    for (Plato x : respuesta) {
                        // Crear una nueva CardView para cada plato
                        CardView cardView = new CardView(MostrarEPlatosActivity.this);
                        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        cardParams.setMargins(0, 0, 0, 16); // Margen inferior entre las tarjetas
                        cardView.setLayoutParams(cardParams);
                        cardView.setCardBackgroundColor(Color.parseColor("#9F8772"));
                        cardView.setRadius(8); // Radio de esquinas de la tarjeta
                        cardView.setPadding(16, 16, 16, 16); // Relleno interno de la tarjeta
                        cardView.setContentPadding(16, 16, 16, 16); // Relleno interno del contenido

                        // Crear un TextView para mostrar los detalles del plato
                        TextView textView = new TextView(MostrarEPlatosActivity.this);
                        String platoId = getString(R.string.plato_id, String.valueOf(x.idPlato));
                        String platoNombre = getString(R.string.plato_nombre, x.nombre);
                        String platoPrecio = getString(R.string.plato_precio, String.valueOf(x.precio));
                        String platoDescripcion = getString(R.string.plato_descripcion, x.descripcion);
                        String platoDetalle = getString(R.string.plato_detalle, x.detallePedidos);
                        String platoMenu = getString(R.string.plato_menu, String.valueOf(x.menuDelDia));

                        String platoText = String.format("%s\n%s\n%s\n%s\n%s\n%s", platoId, platoNombre, platoPrecio, platoDescripcion, platoDetalle, platoMenu);
                        textView.setText(platoText);

                        cardView.addView(textView);


                        // Agregar la tarjeta al LinearLayout
                        cardDeck.addView(cardView);
                    }
                } else {
                    Toast.makeText(MostrarEPlatosActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Plato>> call, Throwable t) {
                Toast.makeText(MostrarEPlatosActivity.this, "Ocurrió un error", Toast.LENGTH_LONG).show();
            }
        });
    }

}