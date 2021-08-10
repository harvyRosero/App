package com.example.myapplication3.pojo;

public class UserComentarios {

    String nombre;
    String comentario;
    String correo;
    String imagen;
    String lugar;

    public UserComentarios() {
    }

    public UserComentarios(String nombre, String comentario, String correo, String imagen, String lugar) {
        this.nombre = nombre;
        this.comentario = comentario;
        this.correo = correo;
        this.imagen = imagen;
        this.lugar = lugar;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
}
