import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ArchivoPersona gestor=new ArchivoPersona();
        iniciar(gestor,sc);
    }

    public static void menu(){
        System.out.println("--SISTEMA DE ARCHIVO DE PERSONAS--");
        System.out.println("Elija su opción");
        System.out.println("1. Agregar persona");
        System.out.println("2. Mostrar lista de personas");
        System.out.println("3. Buscar por DNI");
        System.out.println("4. Calcular el promedio de altura");
        System.out.println("5. Generar un archivo con las personas con altura mayor a 160cm");
        System.out.println("0. Cerrar");
    }

    public static void iniciar(ArchivoPersona gestor, Scanner sc){
        menu();
        int opcion = sc.nextInt();
        sc.nextLine();
        while (opcion!=0) {
            opciones(opcion,gestor,sc);
            menu();
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
            case 3:
                buscarDNI(sc,gestor);
                break;
            case 4:
                System.out.println("Promedio de altura: " + gestor.promedio());
            case 5:
                alturaMayorA(gestor);
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

    public static void buscarDNI(Scanner sc, ArchivoPersona gestor){
        System.out.println("Ingrese el DNI: ");
        gestor.mostrar(gestor.buscar(Integer.parseInt(sc.nextLine())));;
    }

    public static void alturaMayorA(ArchivoPersona gestor){
        gestor.comprobarAltura(gestor.alturaMayorA());
    }


}
