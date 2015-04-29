package com.fe.mobile.news;

import java.io.Serializable;

/**
 * Created by dgarcia on 24/04/2015.
 */
public class NewJson implements Serializable{



    private Long noticia_id;
    private String noticia_titulo;
    private String noticia_bajada;
    private String noticia_fecha;
    private String noticia_url_image;
    private String noticia_cuerpo;

    public Long getNoticia_id() {
        return noticia_id;
    }

    public void setNoticia_id(Long noticia_id) {
        this.noticia_id = noticia_id;
    }

    public String getNoticia_titulo() {
        return noticia_titulo;
    }

    public void setNoticia_titulo(String noticia_titulo) {
        this.noticia_titulo = noticia_titulo;
    }

    public String getNoticia_bajada() {
        return noticia_bajada;
    }

    public void setNoticia_bajada(String noticia_bajada) {
        this.noticia_bajada = noticia_bajada;
    }

    public String getNoticia_fecha() {
        return noticia_fecha;
    }

    public void setNoticia_fecha(String noticia_fecha) {
        this.noticia_fecha = noticia_fecha;
    }

    public String getNoticia_url_image() {
        return noticia_url_image;
    }

    public void setNoticia_url_image(String noticia_url_image) {
        this.noticia_url_image = noticia_url_image;
    }

    public String getNoticia_cuerpo() {
        return noticia_cuerpo;
    }

    public void setNoticia_cuerpo(String noticia_cuerpo) {
        this.noticia_cuerpo = noticia_cuerpo;
    }

    @Override
    public String toString() {
        return "NewJson{" +
                "noticia_id=" + noticia_id +
                ", noticia_titulo='" + noticia_titulo + '\'' +
                ", noticia_bajada='" + noticia_bajada + '\'' +
                ", noticia_fecha='" + noticia_fecha + '\'' +
                ", noticia_url_image='" + noticia_url_image + '\'' +
                ", noticia_cuerpo='" + noticia_cuerpo + '\'' +
                '}';
    }
}
