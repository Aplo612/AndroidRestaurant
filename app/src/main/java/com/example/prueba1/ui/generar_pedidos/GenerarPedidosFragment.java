package com.example.prueba1.ui.generar_pedidos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.prueba1.R;
import com.example.prueba1.databinding.FragmentGenerarPedidosBinding;

public class GenerarPedidosFragment extends Fragment {

    private FragmentGenerarPedidosBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GenerarPedidosViewModel grabarPedidosViewModel =
                new ViewModelProvider(this).get(GenerarPedidosViewModel.class);

        binding = FragmentGenerarPedidosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        Button button = root.findViewById(R.id.button);
        Button button2 = root.findViewById(R.id.button2);
        Button button3 = root.findViewById(R.id.button3);
        Button button4 = root.findViewById(R.id.button4);
        Button button5 = root.findViewById(R.id.button5);
        Button button6 = root.findViewById(R.id.button6);
        Button button7 = root.findViewById(R.id.button7);
        Button button8 = root.findViewById(R.id.button8);
        Button button9 = root.findViewById(R.id.button9);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numeroMesa = 1;

                // Aquí se ejecutará el código al presionar el botón
                Intent intent = new Intent(getActivity(), RealizarPedidoActivity.class);
                intent.putExtra("numeroMesa", 1);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numeroMesa = 2;

                // Aquí se ejecutará el código al presionar el botón
                Intent intent = new Intent(getActivity(), RealizarPedidoActivity.class);
                intent.putExtra("numeroMesa", 2);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numeroMesa = 3;

                // Aquí se ejecutará el código al presionar el botón
                Intent intent = new Intent(getActivity(), RealizarPedidoActivity.class);
                intent.putExtra("numeroMesa", 3);
                startActivity(intent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numeroMesa = 4;

                // Aquí se ejecutará el código al presionar el botón
                Intent intent = new Intent(getActivity(), RealizarPedidoActivity.class);
                intent.putExtra("numeroMesa", 4);
                startActivity(intent);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numeroMesa = 5;

                // Aquí se ejecutará el código al presionar el botón
                Intent intent = new Intent(getActivity(), RealizarPedidoActivity.class);
                intent.putExtra("numeroMesa", 5);
                startActivity(intent);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numeroMesa = 6;

                // Aquí se ejecutará el código al presionar el botón
                Intent intent = new Intent(getActivity(), RealizarPedidoActivity.class);
                intent.putExtra("numeroMesa", 6);
                startActivity(intent);
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numeroMesa = 7;

                // Aquí se ejecutará el código al presionar el botón
                Intent intent = new Intent(getActivity(), RealizarPedidoActivity.class);
                intent.putExtra("numeroMesa", 7);
                startActivity(intent);
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numeroMesa = 8;

                // Aquí se ejecutará el código al presionar el botón
                Intent intent = new Intent(getActivity(), RealizarPedidoActivity.class);
                intent.putExtra("numeroMesa", 8);
                startActivity(intent);
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numeroMesa = 9;

                // Aquí se ejecutará el código al presionar el botón
                Intent intent = new Intent(getActivity(), RealizarPedidoActivity.class);
                intent.putExtra("numeroMesa", 9);
                startActivity(intent);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}