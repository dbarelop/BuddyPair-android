package org.aegee_zaragoza.buddypair.data;

import java.util.Date;

/**
 * Created by dbarelop on 01/09/15.
 */
public class Peer extends Student {
    private Date register_date;
    private int peer;
    private Boolean gender_preference;
    private int erasmus_limit;
    private String notes;

    public Peer(int id, String name, String surname, boolean gender, Date birthdate, String nacionality, String email, String phone, String studies, String faculty, Date register_date, int peer, boolean gender_preference, int erasmus_limit, String notes) {
        super(id, name, surname, gender, birthdate, nacionality, email, phone, studies, faculty);
        this.register_date = register_date;
        this.peer = peer;
        this.gender_preference = gender_preference;
        this.erasmus_limit = erasmus_limit;
        this.notes = notes;
    }

    public Date getRegister_date() {
        return register_date;
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
