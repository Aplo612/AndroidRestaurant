package com.example.prueba1.Api;

import com.example.prueba1.Models.Pedido;
import com.example.prueba1.Models.Plato;
import com.example.prueba1.Models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface ServiceAPI {
    //PLATOS
    @GET("Platos")
    public abstract Call<List<Plato>> GetPlatos();
    @GET("Platos/{id}")
    public abstract Call<Plato> GetbyID(@Path("id") int id);
    @POST("Platos/agregar")
    public abstract Call<Plato> AdicionarPlatos(@Body Plato obj);
    @DELETE("Platos/eliminar/{id}")
    public  abstract Call<Plato> eliminarPlatos(@Path("id") int id);
    @PUT("Platos/modificar")
    public abstract Call<Plato> editarDetallesPlatos(@Body Plato obj);

    //USUARIO

    @POST("Usuarios/Registrarse")
    public abstract Call<Usuario> Registrarse(@Body Usuario Obj);
    @POST("Usuarios/IniciarSesion")
    public abstract Call<Usuario> IniciarSesion(@Body Usuario Obj);

    //PEDIDOS

    @GET("Pedidos")
    public abstract Call<List<Pedido>> GetPedidos();
    @GET("Pedidos/{id}")
    public abstract  Call<Pedido> GetbyIDPedidos(@Path("id") int id);
    @POST("Pedidos/agregar")
    public  abstract  Call<Pedido> AdicionarPedidos(@Body Pedido obj);
    @PUT("Pedidos/modificar")
    public abstract Call<Pedido> editarDetallesPedidos(@Body Pedido obj);
}
