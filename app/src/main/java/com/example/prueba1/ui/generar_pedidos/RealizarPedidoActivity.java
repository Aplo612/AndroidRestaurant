package com.example.prueba1.ui.generar_pedidos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prueba1.Api.ServiceAPI;
import com.example.prueba1.Conexion.ConnectionREST;
import com.example.prueba1.Models.DetallePedidos;
import com.example.prueba1.Models.Pedido;
import com.example.prueba1.Models.Plato;
import com.example.prueba1.R;
import com.example.prueba1.ui.editar_platos.MostrarEPlatosActivity;
import com.example.prueba1.ui.ver_pedidos.VerPedidosActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RealizarPedidoActivity extends AppCompatActivity {

    private ServiceAPI serviceAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_pedido);

        serviceAPI = ConnectionREST.getConnection().create(ServiceAPI.class);

        int numeroMesa = getIntent().getIntExtra("numeroMesa", 0);
        TextView textView = findViewById(R.id.textViewMesa);
        textView.setText("Número de mesa: " + numeroMesa);
        load();
    }


    public void load() {
        Call<List<Plato>> call = serviceAPI.GetPlatos();

        call.enqueue(new Callback<List<Plato>>() {
            @Override
            public void onResponse(Call<List<Plato>> call, Response<List<Plato>> response) {

                if (response.isSuccessful()) {
                    List<Plato> respuesta = response.body();

                    LinearLayout cardDeck = findViewById(R.id.cardDeck2);
                    cardDeck.removeAllViews(); // Limpiar cualquier vista existente


                    for (Plato x : respuesta) {
                        if (x.menuDelDia == true) {
                            // Crear una nueva CardView para cada plato
                            CardView cardView = new CardView(RealizarPedidoActivity.this);
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

                            // Crear un LinearLayout para contener los detalles del pedido
                            LinearLayout detailsLayout = new LinearLayout(RealizarPedidoActivity.this);
                            detailsLayout.setOrientation(LinearLayout.VERTICAL);

                            // Crear un TextView para mostrar los detalles del plato
                            TextView textView = new TextView(RealizarPedidoActivity.this);
                            String platoNombre = getString(R.string.plato_nombre, x.nombre);
                            String platoDescripcion = getString(R.string.plato_descripcion, x.descripcion);
                            String platoPrecio = getString(R.string.plato_precio, String.valueOf(x.precio));

                            String platoText = String.format("%s\n%s\n%s", platoNombre, platoDescripcion, platoPrecio);
                            textView.setText(platoText);

                            detailsLayout.addView(textView);

                            // Agregar una línea divisoria
                            View lineView = new View(RealizarPedidoActivity.this);
                            LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    1 // Altura en píxeles
                            );
                            lineView.setLayoutParams(lineParams);
                            lineView.setBackgroundColor(Color.BLACK); // Color de la línea
                            detailsLayout.addView(lineView);

                            // Agregar un LinearLayout que contenga el botón "Agregar"
                            LinearLayout buttonLayout = new LinearLayout(RealizarPedidoActivity.this);
                            buttonLayout.setGravity(Gravity.CENTER);
                            LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT
                            );
                            buttonLayoutParams.setMargins(0, 16, 0, 0); // Margen superior entre el botón y la línea divisoria
                            buttonLayout.setLayoutParams(buttonLayoutParams);

                            // Crear el botón "Agregar" para cada plato
                            Button button = new Button(RealizarPedidoActivity.this);
                            button.setText("Agregar");

                            // Configurar el evento onClick para el botón "Agregar"
                            button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    // Aquí se ejecutará el código al presionar el botón "Agregar"
                                    // Puedes agregar la lógica necesaria para agregar el plato al pedido
                                }
                            });

                            buttonLayout.addView(button);

                            // Agregar el LinearLayout del botón al LinearLayout de los detalles del pedido
                            detailsLayout.addView(buttonLayout);

                            cardView.addView(detailsLayout);

                            // Agregar la tarjeta al LinearLayout
                            cardDeck.addView(cardView);
                        }
                    }

                } else {
                    Toast.makeText(RealizarPedidoActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Plato>> call, Throwable t) {
                Toast.makeText(RealizarPedidoActivity.this, "Ocurrió un error", Toast.LENGTH_LONG).show();
            }
        });
    }
}