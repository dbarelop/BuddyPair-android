package org.aegee_zaragoza.buddypair.database;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dbarelop on 01/09/15.
 */
public class ErasmusTable {
    public static final String TABLE_NAME = "ERASMUS";
    public static final String PRIMARY_KEY = "id";
    public static final String COLUMN_REGISTER_DATE = "register_date";
    public static final String COLUMN_ERASMUS = "erasmus";
    public static final String COLUMN_GENDER_PREFERENCE = "gender_preference";
    public static final String COLUMN_ARRIVAL_DATE = "arrival_date";
    public static final String COLUMN_NOTES = "notes";

    public static final Set<String> AVAILABLE_COLUMNS = new HashSet<>(Arrays.asList(new String[]{
            PRIMARY_KEY,
            COLUMN_REGISTER_DATE,
            COLUMN_ERASMUS,
            COLUMN_GENDER_PREFERENCE,
            COLUMN_ARRIVAL_DATE,
            COLUMN_NOTES
    }));
}
