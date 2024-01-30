package mvc;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;

import mysql.Database;

public class Equipo {
    private String id;
    private String nombre;
    private String categoria;
    private String costo;
    private String horsePower;

    public Equipo(String id, String nombre, String categoria, String costo, String horsePower) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.costo = costo;
        this.horsePower = horsePower;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(String horsePower) {
        this.horsePower = horsePower;
    }

    public void saveEquipo(Equipo equipo) throws SQLException {
        Database database = new Database();

        try (Connection db = database.getConnection()) {
            DatabaseMetaData dbm = db.getMetaData();
            ResultSet tables = dbm.getTables(null, null, "equipo", null);
            if (tables.next()) {
                String sql = "INSERT INTO equipo (id, nombre, categoria, costo, horsePower) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement statement = db.prepareStatement(sql);
                statement.setString(1, equipo.getId());
                statement.setString(2, equipo.getNombre());
                statement.setString(3, equipo.getCategoria());
                statement.setString(4, equipo.getCosto());
                statement.setString(5, equipo.getHorsePower());
                try {
                    statement.executeUpdate();
                    System.out.println("Equipo guardado");
                } catch (SQLIntegrityConstraintViolationException e) {
                    throw new SQLException("Error: ID Existente");
                }
            } else {
                String createTableSql = "CREATE TABLE equipo (id VARCHAR(255) PRIMARY KEY UNIQUE, nombre VARCHAR(255), categoria VARCHAR(255), costo VARCHAR(255), horsePower VARCHAR(255))";
                PreparedStatement createTableStatement = db.prepareStatement(createTableSql);
                createTableStatement.executeUpdate();
                System.out.println("La tabla 'equipo' ha sido creada");

                saveEquipo(equipo);
            }
        }
    }

    public Equipo findById(String id) throws SQLException {
        Database database = new Database();
        try (Connection db = database.getConnection()) {
            String sql = "SELECT * FROM equipo WHERE id = ?";
            PreparedStatement statement = db.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Equipo equipo = new Equipo(null, null, null, null, null);
                equipo.setId(resultSet.getString("id"));
                equipo.setNombre(resultSet.getString("nombre"));
                equipo.setCategoria(resultSet.getString("categoria"));
                equipo.setCosto(resultSet.getString("costo"));
                equipo.setHorsePower(resultSet.getString("horsePower"));
                return equipo;
            } else {
                return null;
            }
        }
    }
}