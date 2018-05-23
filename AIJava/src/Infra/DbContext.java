package Infra;

import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbContext {

    public DbContext() {

    }

    public boolean ExecuteQuery(String query) {
        Statement stmt = null;
        try {
            Connection c = Open();
            stmt = c.createStatement();

            stmt.executeUpdate(query);
            stmt.close();

            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully");

        return true;
    }

    public ResultSet ExecuteQuerySelect(String query) {

        Statement stmt = null;
        try {
            Connection c = Open();
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            rs.close();
            stmt.close();
            c.close();
            System.out.println("Operation done successfully");
            return rs;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }

    public Connection Open() {

        try {
            Connection c = null;

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);

            System.out.println("Opened database successfully");

            return c;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return null;
    }
}
