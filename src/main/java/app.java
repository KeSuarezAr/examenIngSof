import mvc.Equipo;
import mvc.EquipoView;
import mvc.EquipoController;

import java.sql.Connection;
import java.sql.SQLException;

import mysql.Database;

public class app {
    public static void main(String[] args) throws SQLException {
        try (Connection db = connectToDatabase()) {
            System.out.println(db != null ? "Connected to database" : "Failed to connect to database");
        }

        EquipoView view = new EquipoView();
        Equipo equipo = new Equipo("1", "Equipo 1", "Categoria 1", "1000", "100");

        EquipoController controller = new EquipoController(equipo, view);
        controller.save();

        Equipo equipoDesdeLaBaseDeDatos = new Equipo(null, null, null, null, null);
        equipoDesdeLaBaseDeDatos = equipoDesdeLaBaseDeDatos.findById("1");

        EquipoController controller2 = new EquipoController(equipoDesdeLaBaseDeDatos, view);
        controller2.updateView();

    }

    public static Connection connectToDatabase() {
        Database database = new Database();
        try {
            return database.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
