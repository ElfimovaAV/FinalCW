package model;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBconnection {
    public Connection getConnection() throws SQLException, IOException {
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("src\\main\\java\\model\\database.properties"))) {
            props.load(in);
        }
        
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");

        return DriverManager.getConnection(url, username, password);
    }

    public void closeConection(Connection connection) throws SQLException {
        connection.close();
    }

    public ResultSet getResultSet(String query) throws SQLException, IOException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        return rs;
    }

    public void executeQuery(String query, int animal_type_id, int kinds_of_animals_id, String name, Date birthday,
            String command, int id) throws SQLException, IOException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, animal_type_id);
        ps.setInt(2, kinds_of_animals_id);
        ps.setString(3, name);
        ps.setDate(4, birthday);
        ps.setString(5, command);
        ps.setInt(6, id);
        ps.executeUpdate();
    }

    public void executeQuery(String query, int animal_type_id, int kinds_of_animals_id, String name, Date birthday,
                             String command ) throws SQLException, IOException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, animal_type_id);
        ps.setInt(2, kinds_of_animals_id);
        ps.setString(3, name);
        ps.setDate(4, birthday);
        ps.setString(5, command);
        ps.executeUpdate();
    }
}