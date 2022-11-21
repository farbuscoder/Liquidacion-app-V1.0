package files;
import java.io.*;
import java.util.Date;

public class manageFiles {

    //Con este metodo creamos un archivo, y tiene como parametro el path del archivo que creamos.
    public static void createFile(String fileName){

        File file = new File(fileName);

        //Si ya existe, mostramos un cadena vacia.
        if(file.exists()){
        System.out.println("");
        }else{
            try {
                PrintWriter output = new PrintWriter(file);
                output.close();
                System.out.println("El archivo log ha sido creado");
            } catch (FileNotFoundException e) {
                e.printStackTrace(System.out);
            }
        }
    }

    //Con este metodo escribimos el archivo, tiene como parametros el path del archivo y el contenido que queremos escribir
    public static void escribirArchivo(String fileName, String content){
        File file = new File(fileName);

        //Objeto de tipo Date para registrar la fecha en el log.
        Date fecha = new Date();

        //Verifica si el archivo existe con el metodo exists() que retorna un booleano.
    if(file.exists()){
        try {
            PrintWriter output = new PrintWriter(new FileWriter(file, true));
            output.println("["+fecha+"]" + content);
            output.close();
            System.out.println("El archivo ha sido escrito");
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }else{
        System.out.println("El archivo no existe");
    }
    }


    //Este metodo nos permite leer el archivo y mostrarlo por consola.
    public static void leerArchivo(String fileName){
        File file = new File(fileName);

        //Verifica si el archivo existe con el metodo exists() que retorna un booleano.
    if(file.exists()){
        try {
            BufferedReader input = new BufferedReader(new FileReader(file));
            String lectura = input.readLine();
            while(lectura != null){
                System.out.println(lectura);
                lectura = input.readLine();
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }else{
        System.out.println("El archivo no existe");
    }

    }

    //Eliminamos el archivo en caso de que se requiera, tiene el path del archivo como parametro.
    public static void borrarArchivo(String fileName) {
        File file = new File(fileName);

        //Verifica si el archivo existe con el metodo exists() que retorna un booleano.
        if(file.exists()){
            file.delete();
            System.out.println("El archivo ha sido borrado");
        }else{
            System.out.println("El archivo no existe");
        }

    }


}
