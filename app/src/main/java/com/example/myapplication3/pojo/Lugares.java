package com.example.myapplication3.pojo;

import android.net.Uri;

public class Lugares {

    private String nombre;
    private String ubicacion;
    private String descripcion;
    private String imagen;
    private String recomendaciones;
    private String clima;

    public Lugares() {
    }

    public Lugares(String nombre, String ubicacion, String descripcion, String imagen, String recomendaciones,
                   String clima) {

        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.recomendaciones = recomendaciones;
        this.clima = clima;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }
}
