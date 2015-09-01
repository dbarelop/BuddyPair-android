package org.aegee_zaragoza.buddypair.database;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dbarelop on 01/09/15.
 */
public class CountryTable {
    public static final String TABLE_NAME = "COUNTRY";
    public static final String PRIMARY_KEY = "country_code";
    public static final String COLUMN_COUNTRY_NAME = "country_name";

    public static final Set<String> AVAILABLE_COLUMNS = new HashSet<>(Arrays.asList(new String[]{
            PRIMARY_KEY,
            COLUMN_COUNTRY_NAME
    }));
}
