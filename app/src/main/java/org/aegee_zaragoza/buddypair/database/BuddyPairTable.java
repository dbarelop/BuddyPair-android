package org.aegee_zaragoza.buddypair.database;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dbarelop on 01/09/15.
 */
public class BuddyPairTable {
    public static final String TABLE_NAME = "BUDDY_PAIR";
    public static final String COLUMN_ERASMUS = "erasmus";
    public static final String COLUMN_PEER = "peer";
    public static final String COLUMN_NOTIFIED_ERASMUS = "notified_erasmus";
    public static final String COLUMN_NOTIFIED_PEER = "notified_peer";

    public static final Set<String> AVAILABLE_COLUMNS = new HashSet<>(Arrays.asList(new String[]{
            COLUMN_ERASMUS,
            COLUMN_PEER,
            COLUMN_NOTIFIED_ERASMUS,
            COLUMN_NOTIFIED_PEER
    }));
}
