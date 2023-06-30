package com.example.prueba1.Models;

import java.util.List;

public class Plato {
    public int idPlato;
    public String nombre;
    public String descripcion;
    public int precio;
    public boolean menuDelDia;
    public List<String> detallePedidos;

    public Plato() {

    }

    public Plato(int idPlato, String nombre, String descripcion, int precio, boolean menuDelDia, List<String> detallePedidos) {
        this.idPlato = idPlato;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.menuDelDia = menuDelDia;
        this.detallePedidos = detallePedidos;
    }

    public int getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(int idPlato) {
        this.idPlato = idPlato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public boolean isMenuDelDia() {
        return menuDelDia;
    }

    public void setMenuDelDia(boolean menuDelDia) {
        this.menuDelDia = menuDelDia;
    }

    public List<String> getDetallePedidos() {
        return detallePedidos;
    }

    public void setDetallePedidos(List<String> detallePedidos) {
        this.detallePedidos = detallePedidos;
    }
}
