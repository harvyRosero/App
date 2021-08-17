package com.example.myapplication3.pojo;

public class Usuarios {
    String nombre;
    String correo;
    String numero;
    String nacionalidad;

    public Usuarios() {
    }

    public Usuarios(String nombre, String correo, String numero, String nacionalidad) {
        this.nombre = nombre;
        this.correo = correo;
        this.numero = numero;
        this.nacionalidad = nacionalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
}
