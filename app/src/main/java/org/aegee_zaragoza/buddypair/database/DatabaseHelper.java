package org.aegee_zaragoza.buddypair.database;

import android.util.Log;

import org.aegee_zaragoza.buddypair.data.Peer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dbarelop on 1/9/15.
 */
public class DatabaseHelper {
    private static final String HOST = "aegee-zaragoza.org";
    private static final int PORT = 3306;
    private static final String DATABASE = "buddy_pair";
    private static Connection conn;

    public static boolean connect(String host, int port, String database, String username, String password) {
        String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, username, password);
            return true;
        } catch (SQLException e) {
            Log.d(e.getMessage(), e.toString(), e);
            return false;
        } catch (Exception e) {
            Log.e(e.getMessage(), e.toString(), e);
            return false;
        }
    }

    public static boolean connect(String username, String password) {
        return connect(HOST, PORT, DATABASE, username, password);
    }

    /* QUERIES */
    private static final String QUERY_PEERS_LIST = "select * from PEER inner join STUDENT on PEER.peer = STUDENT.id";

    public static List<Peer> getPeers() {
        try (PreparedStatement stmt = conn.prepareStatement(QUERY_PEERS_LIST);
             ResultSet rs = stmt.executeQuery()) {
            List<Peer> res = new ArrayList<>();
            while (rs.next()) {
                Peer p = extractPeer(rs);
                res.add(p);
            }
            return res;
        } catch (SQLException e) {
            Log.e(e.getMessage(), e.toString(), e);
            return null;
        }
    }

    private static Peer extractPeer(ResultSet rs) throws SQLException {
        // TODO: handle joins
        int id = rs.getInt(PeerTable.PRIMARY_KEY);
        String name = rs.getString(StudentTable.COLUMN_NAME);
        String surname = rs.getString(StudentTable.COLUMN_SURNAME);
        boolean gender = rs.getBoolean(StudentTable.COLUMN_GENDER);
        Date birthdate = rs.getDate(StudentTable.COLUMN_BIRTHDATE);
        String nacionality = rs.getString(StudentTable.COLUMN_NACIONALITY);
        String email = rs.getString(StudentTable.COLUMN_EMAIL);
        String phone = rs.getString(StudentTable.COLUMN_PHONE);
        String studies = rs.getString(StudentTable.COLUMN_STUDIES);
        String faculty = rs.getString(StudentTable.COLUMN_FACULTY);
        Date register_date = rs.getDate(PeerTable.COLUMN_REGISTER_DATE);
        int peer = rs.getInt(PeerTable.COLUMN_PEER);
        Boolean gender_preference = rs.getBoolean(PeerTable.COLUMN_GENDER_PREFERENCE);
        if (rs.wasNull()) {
            gender_preference = null;
        }
        int erasmus_limit = rs.getInt(PeerTable.COLUMN_ERASMUS_LIMIT);
        String notes = rs.getString(PeerTable.COLUMN_NOTES);
        Peer p = new Peer(id, name, surname, gender, birthdate, nacionality, email, phone, studies, faculty, register_date, peer, gender_preference, erasmus_limit, notes);
        return p;
    }
}
