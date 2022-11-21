package files;
import java.io.*;

public class manageFiles {

    public static void createFile(String fileName){
        File file = new File(fileName);

        try {
            PrintWriter output = new PrintWriter(file);
            output.close();
            System.out.println("El archivo ha sido creado");
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        }
    }

    public static void escribirArchivo(String fileName, String content){
        File file = new File(fileName);

        try {
            PrintWriter output = new PrintWriter(new FileWriter(file, true));
            output.println(content);
            output.close();
            System.out.println("El archivo ha sido escrito");
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void leerArchivo(String fileName){
        File file = new File(fileName);

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
    }

    public static void borrarArchivo(String fileName){
        File file = new File(fileName);
        file.delete();
        System.out.println("El archivo ha sido borrado");

    }


}
