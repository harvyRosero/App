package com.example.myapplication3.pojo;

public class Lugares {

    private String nombre;
    private String ubicacion;
    private String descripcion;
    private String url_imagen;

    public Lugares() {
    }

    public Lugares(String nombre, String ubicacion, String descripcion, String url_imagen ) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.url_imagen = url_imagen;
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

    public String getUrl_imagen() { return url_imagen; }

    public void setUrl_imagen(String url_imagen) { this.url_imagen = url_imagen; }
}
