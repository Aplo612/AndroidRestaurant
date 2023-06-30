package com.example.prueba1.ui.ver_pedidos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prueba1.Api.ServiceAPI;
import com.example.prueba1.Conexion.ConnectionREST;
import com.example.prueba1.Models.DetallePedidos;
import com.example.prueba1.Models.Pedido;
import com.example.prueba1.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerPedidosActivity extends AppCompatActivity {

    private ServiceAPI serviceAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_pedidos);

        serviceAPI = ConnectionREST.getConnection().create(ServiceAPI.class);

        load();
    }

    public void load() {
        Call<List<Pedido>> call = serviceAPI.GetPedidos();

        call.enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {
                if (response.isSuccessful()) {
                    List<Pedido> pedidos = response.body();

                    LinearLayout cardDeck = findViewById(R.id.cardDeck);
                    cardDeck.removeAllViews(); // Limpiar cualquier vista existente

                    for (Pedido pedido : pedidos) {
                        // Crear una nueva CardView para cada pedido
                        CardView cardView = new CardView(VerPedidosActivity.this);
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
                        LinearLayout detailsLayout = new LinearLayout(VerPedidosActivity.this);
                        detailsLayout.setOrientation(LinearLayout.VERTICAL);

                        // Crear un TextView para mostrar el ID del pedido
                        TextView textViewId = new TextView(VerPedidosActivity.this);
                        textViewId.setText("ID del pedido: " + pedido.getIdPedido());
                        detailsLayout.addView(textViewId);

                        // Crear un TextView para mostrar la fecha del pedido
                        TextView textViewFecha = new TextView(VerPedidosActivity.this);
                        textViewFecha.setText("Fecha del pedido: " + pedido.getFecha());
                        detailsLayout.addView(textViewFecha);

                        // Crear un TextView para mostrar el estado del pedido
                        TextView textViewEstado = new TextView(VerPedidosActivity.this);
                        textViewEstado.setText("Estado del pedido: " + pedido.getEstado());
                        detailsLayout.addView(textViewEstado);

                        // Crear un TextView para mostrar el número de mesa
                        TextView textViewMesa = new TextView(VerPedidosActivity.this);
                        textViewMesa.setText("Número de mesa: " + pedido.getMesa());
                        detailsLayout.addView(textViewMesa);

                        // Agregar el LinearLayout de detalles a la tarjeta
                        cardView.addView(detailsLayout);

                        // Obtener la lista de DetallePedidos del pedido
                        List<DetallePedidos> detalles = pedido.getDetallePedidos();

                        for (DetallePedidos detalle : detalles) {
                            // Crear un TextView para cada detalle
                            TextView textViewDetalless = new TextView(VerPedidosActivity.this);

                            // Convertir el detalle en una cadena
                            String detalleStr = "ID del Detalle: " + detalle.getIdDetallePedido() + "\n" +
                                    "ID del Pedido: " + detalle.getIdPedido() + "\n" +
                                    "ID del Plato: " + detalle.getIdPlato() + "\n" +
                                    "Cantidad: " + detalle.getCantidad() + "\n" +
                                    "Precio: " + detalle.getPrecio();

                            textViewDetalless.setText(detalleStr);

                            // Agregar el TextView al layout de detalles
                            detailsLayout.addView(textViewDetalless);

                            // Agregar una línea divisoria
                            View lineView = new View(VerPedidosActivity.this);
                            LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    1  // Altura en píxeles
                            );
                            lineView.setLayoutParams(lineParams);
                            lineView.setBackgroundColor(Color.BLACK);  // Color de la línea
                            detailsLayout.addView(lineView);
                        }


                        // Agregar la tarjeta al LinearLayout
                        cardDeck.addView(cardView);
                    }


                } else {
                    Toast.makeText(VerPedidosActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {
                Toast.makeText(VerPedidosActivity.this, "Ocurrió un error", Toast.LENGTH_LONG).show();
            }
        });
    }
}