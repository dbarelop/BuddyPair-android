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
    private static final String QUERY_PEERS_LIST =
            "select STUDENT.*, PEER.id as peer_id, PEER.*, COUNTRY.country_name as country_name, STUDIES.name as studies_name, FACULTY.name as faculty_name " +
            "from STUDENT " +
            "inner join PEER on STUDENT.id = PEER.peer " +
            "inner join COUNTRY on STUDENT.nacionality = COUNTRY.country_code " +
            "inner join STUDIES on STUDENT.studies = STUDIES.id " +
            "inner join FACULTY on STUDENT.faculty = FACULTY.id";

    public static List<Peer> getPeers() {
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY_PEERS_LIST)) {
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
        int id = rs.getInt("peer_id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        boolean gender = rs.getBoolean("gender");
        Date birthdate = rs.getDate("birthdate");
        String nacionality = rs.getString("country_name");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        String studies = rs.getString("studies_name");
        String faculty = rs.getString("faculty_name");
        Date register_date = rs.getDate("register_date");
        int peer = rs.getInt("peer");
        Boolean gender_preference = rs.getBoolean("gender_preference");
        if (rs.wasNull()) {
            gender_preference = null;
        }
        int erasmus_limit = rs.getInt("erasmus_limit");
        String notes = rs.getString("notes");
        Peer p = new Peer(id, name, surname, gender, birthdate, nacionality, email, phone, studies, faculty, register_date, peer, gender_preference, erasmus_limit, notes);
        return p;
    }
}
