package com.example.myapplication3.pojo;

import android.net.Uri;

public class Lugares {

    private String nombre;
    private String ubicacion;
    private String descripcion;
    private String urlimagen;

    public Lugares() {
    }

    public Lugares(String nombre, String ubicacion, String descripcion, String urlimagen ) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.urlimagen = urlimagen;
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

    public String getUrlimagen() { return urlimagen; }

    public void setUrlimagen(String urlimagen) { this.urlimagen = urlimagen; }
}
