package com.example.myapplication3.pojo;

public class UserImage {
    String ImagePerfil;
    String correo;

    public UserImage() {
    }

    public UserImage(String imagePerfil, String correo) {
        this.ImagePerfil = imagePerfil;
        this.correo = correo;
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
}
