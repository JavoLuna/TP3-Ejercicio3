import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


public class ArchivoPersona {
    RandomAccessFile archivo;
    private final static int tamNom=17;
    private final static int tamDni=4;
    private final static int tamAlt=4;
    private final static int tamTotal=tamNom+tamDni+tamAlt;

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
            archivo.writeInt(p.getAltura());
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
                    System.out.println("Altura: " + archivo.readInt());
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

    public long buscar(int dni){
        long aux = -1;
        
        if (dni<0) {
            System.out.println("No existe ese DNI");
        }else{
            try{
                if (archivo.length()==0) {
                    System.out.println("El arhivo está vacio");
                }else{
                    archivo.seek(tamNom);
                    boolean encontrado = false;
                    while (archivo.getFilePointer() <= archivo.length()&& !encontrado){
                        if(dni==archivo.readInt()){
                            aux=archivo.getFilePointer()-(tamDni+tamNom);
                            encontrado=true;
                        }else{
                            archivo.skipBytes(tamAlt+tamNom);
                        }
                        
                    }
                }
            }catch(IOException e){
                System.out.println("Error al buscar: " + e.getMessage());
            }
        }
        return aux;

    }

    public void mostrar(long posicion){
        if (posicion>0) {
            try{
            archivo.seek(posicion);
            System.out.println("Nombre: " + archivo.readUTF());
            System.out.println("DNI: " + archivo.readInt());
            System.out.println("Altura: " + archivo.readInt());
            }catch(IOException e){
                System.out.println("Error al mostrar: "+e.getMessage());
            }
        }
    }

    public double promedio(){
        int suma=0;
        int contador=0;
        try{
            archivo.seek(tamNom+tamDni);
            while (archivo.getFilePointer()<archivo.length()){
                int valorAltura=archivo.readInt();
                if (valorAltura!=0) {
                    suma+=valorAltura;
                    contador++;
                }
                archivo.skipBytes(tamNom+tamDni);
            }
        }catch (IOException e){
            System.out.println("Error al calcular: " + e.getMessage());
        }
        return (double)suma/contador;
    }

    public RandomAccessFile alturaMayorA(){
        RandomAccessFile auxiliar = null;
        try {
            auxiliar = new RandomAccessFile("auxiliar.dat", "rw");
            archivo.seek(tamNom+tamDni);
            while (archivo.getFilePointer()+tamNom+tamDni<=archivo.length()) {
                if (archivo.readInt()>160) {
                    archivo.skipBytes(-(tamTotal));
                    auxiliar.writeUTF(archivo.readUTF());
                    auxiliar.writeInt(archivo.readInt());
                    auxiliar.writeInt(archivo.readInt());
                }
                archivo.skipBytes(tamNom+tamDni);
            }
        } catch (IOException e) {
            System.out.println("No se pudo calcular:" + e.getMessage());
        }
        return auxiliar;
    }

    public void comprobarAltura(RandomAccessFile auxiliar){
        try{
            if (auxiliar.length()>0){
                System.out.println("Archivo creado exitosamente");
                auxiliar.close();
            }
        }catch(IOException e){
            System.out.println("Error al comprobar: " + e.getMessage());
        }
        
    }
}
