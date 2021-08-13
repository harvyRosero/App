package com.example.myapplication3.pojo;

public class EstadoBotones {
    String correo;
    String estado;
    String nombre_lugar;

    public EstadoBotones() {
    }

    public EstadoBotones(String correo, String estado, String nombre_lugar) {
        this.correo = correo;
        this.estado = estado;
        this.nombre_lugar = nombre_lugar;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre_lugar() {
        return nombre_lugar;
    }

    public void setNombre_lugar(String nombre_lugar) {
        this.nombre_lugar = nombre_lugar;
    }
}
