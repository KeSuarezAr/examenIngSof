package mvc;

public class EquipoView {
    public void printStudentDetails(String equipoNombre, String equipoCategoria, String equipoCosto,
            String equipoHorsePower) {
        System.out.println("Equipo: ");
        System.out.println("Nombre: " + equipoNombre);
        System.out.println("Categoria: " + equipoCategoria);
        System.out.println("Costo: " + equipoCosto);
        System.out.println("Horse Power: " + equipoHorsePower);
    }
}
