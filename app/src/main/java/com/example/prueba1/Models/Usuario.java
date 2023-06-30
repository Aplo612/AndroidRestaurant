package com.example.prueba1.Models;

public class Usuario
{
    public int IdUsuario;

    public String NombreCompleto;

    public String Email;

    public String Contraseña;

    public boolean Esadmin;

    public Usuario()
    {

    }
    public Usuario(int idUsuario, String nombreCompleto, String email, String contraseña, boolean esadmin) {
        IdUsuario = idUsuario;
        NombreCompleto = nombreCompleto;
        Email = email;
        Contraseña = contraseña;
        Esadmin = esadmin;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getNombreCompleto() {
        return NombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        NombreCompleto = nombreCompleto;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    public boolean isEsadmin() {
        return Esadmin;
    }

    public void setEsadmin(boolean esadmin) {
        Esadmin = esadmin;
    }
}
