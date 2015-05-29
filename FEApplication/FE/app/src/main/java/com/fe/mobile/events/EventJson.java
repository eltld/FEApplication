package com.fe.mobile.events;

import java.io.Serializable;

/**
 * Created by David Garcia on 29/04/2015.
 */
public class EventJson  implements Serializable{

    private String event_name;
    private String event_username;
    private String event_urlImageDate;
    private String event_message;
    private String event_date;
    private Long event_idEvent;


    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_username() {
        return event_username;
    }

    public void setEvent_username(String event_username) {
        this.event_username = event_username;
    }

    public String getEvent_urlImageDate() {
        return event_urlImageDate;
    }

    public void setEvent_urlImageDate(String event_urlImageDate) {
        this.event_urlImageDate = event_urlImageDate;
    }

    public String getEvent_message() {
        return event_message;
    }

    public void setEvent_message(String event_message) {
        this.event_message = event_message;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public Long getEvent_idEvent() {
        return event_idEvent;
    }

    public void setEvent_idEvent(Long event_idEvent) {
        this.event_idEvent = event_idEvent;
    }

    @Override
    public String toString() {
        return "EventJson{" +
                "event_name='" + event_name + '\'' +
                ", event_username='" + event_username + '\'' +
                ", event_urlImageDate='" + event_urlImageDate + '\'' +
                ", event_message='" + event_message + '\'' +
                ", event_date='" + event_date + '\'' +
                ", event_idEvent='" + event_idEvent + '\'' +
                '}';
    }
}
