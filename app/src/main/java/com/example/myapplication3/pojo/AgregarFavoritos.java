package com.example.myapplication3.pojo;

import android.content.Context;
import android.content.SharedPreferences;

public class AgregarFavoritos {
    String lugar_name;
    String lugar_descripcion;
    String lugar_ubicacion;
    String imagen;
    String mail;
    String longitud;
    String latitud;
    String clima;

    public AgregarFavoritos() {
    }

    public AgregarFavoritos(String lugar_name, String lugar_descripcion, String lugar_ubicacion,
                            String mail, String imagen, String longitud, String latitud, String clima) {
        this.lugar_name = lugar_name;
        this.lugar_descripcion = lugar_descripcion;
        this.lugar_ubicacion = lugar_ubicacion;
        this.mail = mail;
        this.imagen = imagen;
        this.longitud = longitud;
        this.latitud = latitud;
        this.clima = clima;
    }

    public String getLugar_name() {
        return lugar_name;
    }

    public void setLugar_name(String lugar_name) {
        this.lugar_name = lugar_name;
    }

    public String getLugar_descripcion() {
        return lugar_descripcion;
    }

    public void setLugar_descripcion(String lugar_descripcion) {
        this.lugar_descripcion = lugar_descripcion;
    }

    public String getLugar_ubicacion() {
        return lugar_ubicacion;
    }

    public void setLugar_ubicacion(String lugar_ubicacion) {
        this.lugar_ubicacion = lugar_ubicacion;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }
}
