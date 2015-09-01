package org.aegee_zaragoza.buddypair.data;

import java.util.Date;

/**
 * Created by dbarelop on 01/09/15.
 */
public class Erasmus extends Student {
    private Date register_date;
    private int erasmus;
    private Boolean gender_preference;
    private Date arrival_date;
    private String notes;

    public Erasmus(int id, String name, String surname, boolean gender, Date birthdate, String nacionality, String email, String phone, String studies, String faculty, Date register_date, int erasmus, boolean gender_preference, Date arrival_date, String notes) {
        super(id, name, surname, gender, birthdate, nacionality, email, phone, studies, faculty);
        this.register_date = register_date;
        this.erasmus = erasmus;
        this.gender_preference = gender_preference;
        this.arrival_date = arrival_date;
        this.notes = notes;
    }

    public Date getRegister_date() {
        return register_date;
    }

    public int getErasmus() {
        return erasmus;
    }

    public Boolean getGender_preference() {
        return gender_preference;
    }

    public Date getArrival_date() {
        return arrival_date;
    }

    public String getNotes() {
        return notes;
    }
}
