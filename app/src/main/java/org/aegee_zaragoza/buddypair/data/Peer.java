package org.aegee_zaragoza.buddypair.data;

import java.util.Date;

public class Peer extends Student {
    private int peer;
    private Boolean gender_preference;
    private int erasmus_limit;
    private String notes;

    public Peer(int id, String name, String surname, boolean gender, Date birthdate, String nacionality, String email, String phone, String studies, String faculty, Date register_date, int peer, Boolean gender_preference, int erasmus_limit, String notes) {
        super(id, name, surname, gender, birthdate, nacionality, email, phone, studies, faculty, register_date);
        this.peer = peer;
        this.gender_preference = gender_preference;
        this.erasmus_limit = erasmus_limit;
        this.notes = notes;
    }

    public int getPeer() {
        return peer;
    }

    public Boolean getGender_preference() {
        return gender_preference;
    }

    public int getErasmus_limit() {
        return erasmus_limit;
    }

    public String getNotes() {
        return notes;
    }
}
