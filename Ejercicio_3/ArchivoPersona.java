package Ejercicio_3;
import java.io.IOException;
import java.io.RandomAccessFile;


public class ArchivoPersona {
    RandomAccessFile archivo;

    public ArchivoPersona() {
        try {
            archivo = new RandomAccessFile("datos.dat","rw");
        } catch (IOException e) {
            System.out.println("Error al cargar archivo" + e.getMessage());
        }
    }

    public void Agregar(Persona p){
        try{
            archivo.seek(archivo.length());
            if(p.getNombre().length()>=15){
                archivo.writeUTF(p.getNombre().substring(0,15));
            }else{
                archivo.writeUTF(String.format("%-15s",p.getNombre()));
            }
            archivo.writeInt(p.getDni());
            archivo.writeDouble(p.getAltura());
        } catch (IOException e) {
            System.out.println("Error al agregar" + e.getMessage());
        }
    }

    public void listar(){
        try{
            if(archivo.length()==0){
                System.out.println("El archivo está vacio");
                return;
            }else{
                archivo.seek(0);
                while(archivo.getFilePointer()<archivo.length()){
                    System.out.println("Nombre: " + archivo.readUTF());
                    System.out.println("DNI: " + archivo.readInt());
                    System.out.println("Altura: " + archivo.readDouble());
                }
            }
        }catch(IOException e){
            System.out.println("Error al mostrar" + e.getMessage());
        }
    }

    public void cerrar(){
        try{
            archivo.close();
        }catch(IOException e){
            System.out.println("Error al cerrar" + e.getMessage());
        }
    }
}
