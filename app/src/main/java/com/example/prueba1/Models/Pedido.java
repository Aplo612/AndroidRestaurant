package com.example.prueba1.Models;
import java.util.Date;
import java.util.List;

public class Pedido{
    public int idPedido;
    public String fecha;
    public String estado;
    public int mesa;
    public List<String> boleta;
    public List<DetallePedidos> detallePedidos;

    public Pedido() {
    }


    public Pedido(int idPedido, String fecha, String estado, int mesa, List<String> boleta, List<DetallePedidos> detallePedidos) {
        this.idPedido = idPedido;
        this.fecha = fecha;
        this.estado = estado;
        this.mesa = mesa;
        this.boleta = boleta;
        this.detallePedidos = detallePedidos;
    }


    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public List<String> getBoleta() {
        return boleta;
    }

    public void setBoleta(List<String> boleta) {
        this.boleta = boleta;
    }

    public List<DetallePedidos> getDetallePedidos() {
        return detallePedidos;
    }

    public void setDetallePedidos(List<DetallePedidos> detallePedidos) {
        this.detallePedidos = detallePedidos;
    }
}
