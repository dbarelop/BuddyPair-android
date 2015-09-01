package org.aegee_zaragoza.buddypair.database;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dbarelop on 01/09/15.
 */
public class LanguageTable {
    public static final String TABLE_NAME = "LANGUAGE";
    public static final String PRIMARY_KEY = "id";
    public static final String COLUMN_LANGUAGE = "language";

    public static final Set<String> AVAILABLE_COLUMNS = new HashSet<>(Arrays.asList(new String[]{
            PRIMARY_KEY,
            COLUMN_LANGUAGE
    }));
}
