package Ejercicio_3;
class Persona {

    private String nombre;
    private int dni;
    private int altura;

    public Persona(String nombre, int dni, int altura) {
        this.nombre = nombre;
        this.dni = dni;
        this.altura = altura;
    }

    public int getAltura() {
        return altura;
    }

    public int getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }
}
