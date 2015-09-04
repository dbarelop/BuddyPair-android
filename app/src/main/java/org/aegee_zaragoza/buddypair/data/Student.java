package org.aegee_zaragoza.buddypair.data;

import java.io.Serializable;
import java.util.Date;

public abstract class Student implements Serializable {
    private int id;
    private String name;
    private String surname;
    private boolean gender;
    private Date birthdate;
    private String nacionality;
    private String email;
    private String phone;
    private String studies;
    private String faculty;
    private Date register_date;
    private Boolean gender_preference;
    private String notes;

    public Student(int id, String name, String surname, boolean gender, Date birthdate, String nacionality, String email, String phone, String studies, String faculty, Date register_date, Boolean gender_preference, String notes) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.birthdate = birthdate;
        this.nacionality = nacionality;
        this.email = email;
        this.phone = phone;
        this.studies = studies;
        this.faculty = faculty;
        this.register_date = register_date;
        this.gender_preference = gender_preference;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public boolean isMale() {
        return gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String getNacionality() {
        return nacionality;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getStudies() {
        return studies;
    }

    public String getFaculty() {
        return faculty;
    }

    public Date getRegister_date() {
        return register_date;
    }

    public Boolean getGender_preference() {
        return gender_preference;
    }

    public String getNotes() {
        return notes;
    }

    public String toString() {
        return name + " " + surname;
    }
}
