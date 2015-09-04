package org.aegee_zaragoza.buddypair.data;

import java.util.Date;

public class Erasmus extends Student {
    private int erasmus;
    private Date arrival_date;

    public Erasmus(int id, String name, String surname, boolean gender, Date birthdate, String nacionality, String email, String phone, String studies, String faculty, Date register_date, int erasmus, Boolean gender_preference, Date arrival_date, String notes) {
        super(id, name, surname, gender, birthdate, nacionality, email, phone, studies, faculty, register_date, gender_preference, notes);
        this.erasmus = erasmus;
        this.arrival_date = arrival_date;
    }

    public int getErasmus() {
        return erasmus;
    }

    public Date getArrival_date() {
        return arrival_date;
    }
}
