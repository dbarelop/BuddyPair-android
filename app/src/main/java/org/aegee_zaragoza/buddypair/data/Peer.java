package org.aegee_zaragoza.buddypair.data;

import java.util.Date;

public class Peer extends Student {
    private int peer;
    private int erasmus_limit;

    public Peer(int id, String name, String surname, boolean gender, Date birthdate, String nacionality, String email, String phone, String studies, String faculty, Date register_date, int peer, Boolean gender_preference, int erasmus_limit, String notes) {
        super(id, name, surname, gender, birthdate, nacionality, email, phone, studies, faculty, register_date, gender_preference, notes);
        this.peer = peer;
        this.erasmus_limit = erasmus_limit;
    }

    public int getPeer() {
        return peer;
    }

    public int getErasmus_limit() {
        return erasmus_limit;
    }
}
