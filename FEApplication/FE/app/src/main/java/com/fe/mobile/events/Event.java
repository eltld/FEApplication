package com.fe.mobile.events;

import java.io.Serializable;

/**
 * Created by dgarcia on 29/04/2015.
 */
public class Event implements Serializable {

    private String titulo;
    private String name;
    private String username;
    private String message;
    private String date;
    private Long idEvent;
    private String url;
    private String urlImageDate;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUrlImageDate() {
        return urlImageDate;
    }

    public void setUrlImageDate(String urlImageDate) {
        this.urlImageDate = urlImageDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Long idEvent) {
        this.idEvent = idEvent;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", urlImageDate='" + urlImageDate + '\'' +
                ", message='" + message + '\'' +
                ", date='" + date + '\'' +
                ", idEvent=" + idEvent +
                '}';
    }
}
