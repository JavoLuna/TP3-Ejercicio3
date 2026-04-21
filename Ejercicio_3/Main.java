package Ejercicio_3;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ArchivoPersona gestor=new ArchivoPersona();
        iniciar(gestor,sc);
    }

    public static void menu(Scanner sc){
        System.out.println("--SISTEMA DE ARCHIVO DE PERSONAS--");
        System.out.println("Elija su opción");
        System.out.println("1. Agregar persona");
        System.out.println("2. Mostrar lista de personas");
        System.out.println("0. Cerrar");
    }

    public static void iniciar(ArchivoPersona gestor, Scanner sc){
        menu(sc);
        int opcion = sc.nextInt();
        sc.nextLine();
        while (opcion!=0) {
            opciones(opcion,gestor,sc);
            menu(sc);
            opcion = Integer.parseInt(sc.nextLine());
        }
        gestor.cerrar();
    }

    public static void opciones(int opcion,ArchivoPersona gestor,Scanner sc){
        switch (opcion) {
            case 1:
                agregar(gestor,sc);
                break;
            case 2:
                gestor.listar();
                break;
            default:
                System.out.println("Opción incorrecta");
                break;
        }
    }

    public static void agregar(ArchivoPersona gestor, Scanner sc){
        System.out.println("Ingrese nombre de la persona: ");
        String nombre = sc.nextLine();
        System.out.println("Ingrese el DNI: ");
        int dni = Integer.parseInt(sc.nextLine());
        System.out.println("Ingrese la altura en cm: ");
        int altura = Integer.parseInt(sc.nextLine());
        Persona p = new Persona(nombre, dni, altura);
        gestor.Agregar(p);
        System.out.println("Persona agregada exitosamente");
    }


}
