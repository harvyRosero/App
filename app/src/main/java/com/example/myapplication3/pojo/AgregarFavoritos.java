package com.example.myapplication3.pojo;

import android.content.Context;
import android.content.SharedPreferences;

public class AgregarFavoritos {
    String lugar_name;
    String lugar_descripcion;
    String lugar_ubicacion;
    String mail;

    public AgregarFavoritos() {
    }

    public AgregarFavoritos(String lugar_name, String lugar_descripcion, String lugar_ubicacion, String mail) {
        this.lugar_name = lugar_name;
        this.lugar_descripcion = lugar_descripcion;
        this.lugar_ubicacion = lugar_ubicacion;
        this.mail = mail;
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
}
