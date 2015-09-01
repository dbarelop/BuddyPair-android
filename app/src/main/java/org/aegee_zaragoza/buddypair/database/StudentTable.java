package org.aegee_zaragoza.buddypair.database;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dbarelop on 01/09/15.
 */
public class StudentTable {
    public static final String TABLE_NAME = "STUDENT";
    public static final String PRIMARY_KEY = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_BIRTHDATE = "birthdate";
    public static final String COLUMN_NACIONALITY = "nacionality";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_STUDIES = "studies";
    public static final String COLUMN_FACULTY = "faculty";

    public static final Set<String> AVAILABLE_COLUMNS = new HashSet<>(Arrays.asList(new String[]{
        PRIMARY_KEY,
        COLUMN_NAME,
        COLUMN_SURNAME,
        COLUMN_GENDER,
        COLUMN_BIRTHDATE,
        COLUMN_NACIONALITY,
        COLUMN_EMAIL,
        COLUMN_PHONE,
        COLUMN_STUDIES,
        COLUMN_FACULTY
    }));
}
