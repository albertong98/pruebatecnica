package config;

import org.hsqldb.jdbcDriver;

import java.sql.*;

public class Jdbc {
    public static void close(ResultSet rs, Statement st) {
        close(rs);
        close(st);
    }
    public static void close(ResultSet rs) {
        if (rs != null) try { rs.close(); } catch(SQLException e) {/* ignore */}
    }

    public static void close(Statement st) {
        if (st != null ) try { st.close(); } catch(SQLException e) {/* ignore */}
    }


}
