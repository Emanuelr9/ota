package ar.edu.unahur.obj2;

public class Pasajero {
    private String nombre;
    private String apellido;
    private int edad;

    public Pasajero(String nombre, String apellido, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public String getNomnbre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }
}
