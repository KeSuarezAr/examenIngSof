package mvc;

import java.sql.SQLException;

public class EquipoController {
    private final Equipo equipo;
    private final EquipoView view;

    public EquipoController(Equipo equipo, EquipoView view) {
        this.equipo = equipo;
        this.view = view;
    }

    public void setNombre(String name) {
        equipo.setNombre(name);
    }

    public String getNombre() {
        return equipo.getNombre();
    }

    public void setCategoria(String categoria) {
        equipo.setCategoria(categoria);
    }

    public String getCategoria() {
        return equipo.getCategoria();
    }

    public void setCosto(String costo) {
        equipo.setCosto(costo);
    }

    public String getCosto() {
        return equipo.getCosto();
    }

    public void setHorsePower(String horsePower) {
        equipo.setHorsePower(horsePower);
    }

    public String getHorsePower() {
        return equipo.getHorsePower();
    }

    public void updateView() {
        view.printStudentDetails(equipo.getNombre(), equipo.getCategoria(), equipo.getCosto(), equipo.getHorsePower());
    }

    public void save() throws SQLException {
        equipo.saveEquipo(equipo);
    }
}