package com.example.myapplication3.pojo;

public class UserImage {
    String ImagePerfil;
    String correo;
    String nombre;

    public UserImage() {
    }

    public UserImage(String imagePerfil, String correo, String nombre) {
        this.ImagePerfil = imagePerfil;
        this.correo = correo;
        this.nombre = nombre;
    }

    public String getImagePerfil() {
        return ImagePerfil;
    }

    public void setImagePerfil(String imagePerfil) {
        ImagePerfil = imagePerfil;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
